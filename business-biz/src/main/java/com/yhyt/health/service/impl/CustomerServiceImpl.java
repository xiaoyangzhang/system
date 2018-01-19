package com.yhyt.health.service.impl;

import com.alibaba.fastjson.JSON;
import com.yhyt.health.config.PathConfiguration;
import com.yhyt.health.dao.ServiceTaskLogMapper;
import com.yhyt.health.dao.SysServiceTaskMapper;
import com.yhyt.health.model.ServiceTaskLog;
import com.yhyt.health.model.SysServiceTask;
import com.yhyt.health.model.dto.CustomerServiceDTO;
import com.yhyt.health.model.dto.ServiceDetailDTO;
import com.yhyt.health.model.dto.TaskStatisticsDTO;
import com.yhyt.health.model.query.CustomerServiceQuery;
import com.yhyt.health.service.CustomerService;
import com.yhyt.health.util.BusinessException;
import com.yhyt.health.util.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    @Autowired
    private ServiceTaskLogMapper serviceTaskLogMapper;
    @Autowired
    private SysServiceTaskMapper sysServiceTaskMapper;

    @Resource(name = "loadBalanced")
    private RestTemplate restTemplate;
    @Autowired
    private PathConfiguration pathConfiguration;
    @Override
    public Page<CustomerServiceDTO> selectServiceListPage(CustomerServiceQuery query) {
        Page<CustomerServiceDTO> page = new Page<CustomerServiceDTO>();
        if(query.getPageIndex()==null || query.getPageIndex()==0){
            query.setPageIndex(1);
        }
        page.setPageNo(query.getPageIndex());
        if(query.getPageSize() != null && query.getPageSize() != 0){
            page.setPageSize(query.getPageSize());
        }
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("page",page);
        params.put("patientName",query.getPatientName());
        params.put("orderName",query.getOrderName());
        params.put("taskType",query.getTaskType());
        params.put("taskState",query.getTaskState());
        params.put("taskStartTime",query.getTaskStartTime());
        params.put("taskEndTime",query.getTaskEndTime());
        page.setResult(sysServiceTaskMapper.selectServiceListPage(params));
        return page;
    }

    @Override
    public ServiceDetailDTO selectById(Long id) {
        ServiceDetailDTO serviceDetailDTO = sysServiceTaskMapper.selectById(id);
        List<ServiceTaskLog> serviceTaskLogs = serviceTaskLogMapper.selectByTaskId(id);
        if (serviceDetailDTO!=null) {
            if (!CollectionUtils.isEmpty(serviceTaskLogs)){
                serviceDetailDTO.setTaskLogList(serviceTaskLogs);
            }

        }
        return serviceDetailDTO;
    }

    @Override
    @Transactional
    public int releaseTaskByOperator(String operator) {
        List<SysServiceTask> sysServiceTasks = sysServiceTaskMapper.selectByOperator(operator);
        Byte state=1;
        HashMap<String, Object> map = new HashMap<>();
        map.put("operator",operator);
        map.put("now",new Date());
        if (!CollectionUtils.isEmpty(sysServiceTasks)){
            for (SysServiceTask sst:sysServiceTasks){
                //已结束
                /*if (4==sst.getTaskState()) {
                    state=3;
                    map.put("state",state);
                    sysServiceTaskMapper.releaseTaskByOperator(map);
                }*/
                //跟进中
//                else if(2==sst.getTaskState()){
//                    state=1;
                    map.put("state",1);
                    sysServiceTaskMapper.releaseTaskByOperator(map);
//                }

            }
        }
        return 0;
    }

    @Override
    @Transactional
    public int updateTaskState(Long id,Long orderId,Byte state,String content,String operator,String action) {
        logger.info("id={},orderId={},state={},content={},action={}",id,orderId,state,content,action);
        SysServiceTask sysServiceTask = new SysServiceTask();

        if (id!=null){
            ServiceDetailDTO byPrimaryKey = sysServiceTaskMapper.selectById(id);
            if (byPrimaryKey != null) {
                if ("submit".equalsIgnoreCase(action) &&( byPrimaryKey.getOrderState()==null || byPrimaryKey.getOrderState()==1 || byPrimaryKey.getOrderState()==3||
                        byPrimaryKey.getOrderState()==4||byPrimaryKey.getOrderState()==7)){
                    throw new BusinessException("501","订单未付款不能提交医生！");

                }
                //医生处理中或者已完成
                if ("submit".equalsIgnoreCase(action) && null!=byPrimaryKey.getDoctorState() &&( 1==byPrimaryKey.getDoctorState()||2==byPrimaryKey.getDoctorState()) ){
                    throw new BusinessException("501","医生处理中，不可重复提交！");
                }
                if ("end".equals(action) ){
                    if (!(byPrimaryKey.getDoctorState()!=null &&( byPrimaryKey.getDoctorState()==2
                            || byPrimaryKey.getDoctorState()==3 || byPrimaryKey.getTaskState()==5))){
                        throw new BusinessException("501","当前任务状态不能结束任务");
                    }else if(!(byPrimaryKey.getOrderState()==null || 1==byPrimaryKey.getOrderState()
                            || 2==byPrimaryKey.getOrderState() && (byPrimaryKey.getTaskState()==5 || byPrimaryKey.getDoctorState()==null || byPrimaryKey.getDoctorState()==3))){
                        throw new BusinessException("501","当前任务状态不能结束任务");
                    }
                }
                if ("track".equals(action)){
                    if (StringUtils.isNotEmpty(byPrimaryKey.getOperator())){
                        throw  new BusinessException("501","该任务已被他人跟进");
                    }
                }
                if ("refund".equals(action)){//退单
                    if (byPrimaryKey.getOrderState()==null || byPrimaryKey.getOrderState()==1 || byPrimaryKey.getOrderState()==3||
                            byPrimaryKey.getOrderState()==4||byPrimaryKey.getOrderState()==7){
                        throw new BusinessException("501","订单当前状态不能退单");

                    }
                    try {
                        restTemplate.postForEntity(pathConfiguration.getSystemUrl()+"/payment/refund/"+byPrimaryKey.getOrderId()+"?refundMsg={refundMsg}&client={client}",null,null,content,2);
                    } catch (RestClientException e) {
                        logger.error("退单失败，error:{}",e);
                        throw new BusinessException("501","退款失败");
                    }
                }

            }else {
                throw new BusinessException("501","该任务不存在！");
            }

            /*SysServiceTask sysServiceTask = new SysServiceTask();
            sysServiceTask.setId(id);
            sysServiceTask.setUpdateTime(new Date());
            sysServiceTask.setTaskState(state);
            if ("submit".equalsIgnoreCase(action)) {
                sysServiceTask.setDoctorState((byte)1);
                sysServiceTask.setReviewTime(new Date());
            }
            sysServiceTask.setOperator(operator);
            sysServiceTask.setVersion(byPrimaryKey.getVersion());
            sysServiceTaskMapper.updateByPrimaryKeySelective(sysServiceTask);
            ServiceTaskLog serviceTaskLog = new ServiceTaskLog();
            serviceTaskLog.setContent(content);
            serviceTaskLog.setServiceTaskId(id);
            if ("submit".equalsIgnoreCase(action)) state=3;
            else if("end".equalsIgnoreCase(action)) state=6;
            else if("send".equalsIgnoreCase(action)) state=1;
            else if("refund".equalsIgnoreCase(action)) state=2;
            else if("track".equalsIgnoreCase(action)) state=0;//跟进

            serviceTaskLog.setType(state);
            serviceTaskLog.setCreateTime(new Date());
            serviceTaskLog.setOperator(operator);
            logger.info("任务日志，serviceTaskLog：{}", JSON.toJSONString(serviceTaskLog));
            serviceTaskLogMapper.insertSelective(serviceTaskLog);*/
            sysServiceTask.setVersion(byPrimaryKey.getVersion());
        }else if (orderId!=null){
            SysServiceTask serviceTask = sysServiceTaskMapper.selectByOrderId(orderId);
            if (serviceTask==null){
                throw new BusinessException("501","找不到订单对应的任务");
            }else if (!(serviceTask.getDoctorState()==null || serviceTask.getDoctorState()==1)){
                throw new BusinessException("501","当前商品状态不能退款");

            }
            sysServiceTask.setVersion(serviceTask.getVersion());


        }
        sysServiceTask.setId(id);
        sysServiceTask.setUpdateTime(new Date());
        sysServiceTask.setTaskState(state);
        if ("submit".equalsIgnoreCase(action)) {
            sysServiceTask.setDoctorState((byte)1);
            sysServiceTask.setReviewTime(new Date());
        }
        sysServiceTask.setOperator(operator);
        sysServiceTaskMapper.updateByPrimaryKeySelective(sysServiceTask);
        ServiceTaskLog serviceTaskLog = new ServiceTaskLog();
        serviceTaskLog.setContent(content);
        serviceTaskLog.setServiceTaskId(id);
        if ("submit".equalsIgnoreCase(action)) state=3;
        else if("end".equalsIgnoreCase(action)) state=6;
        else if("send".equalsIgnoreCase(action)) state=1;
        else if("refund".equalsIgnoreCase(action)) state=2;
        else if("track".equalsIgnoreCase(action)) state=0;//跟进

        serviceTaskLog.setType(state);
        serviceTaskLog.setCreateTime(new Date());
        serviceTaskLog.setOperator(operator);
        logger.info("任务日志，serviceTaskLog：{}", JSON.toJSONString(serviceTaskLog));
        serviceTaskLogMapper.insertSelective(serviceTaskLog);
        return 0;
    }

    @Override
    public TaskStatisticsDTO selectStatisticsCount(CustomerServiceQuery query) {
        return sysServiceTaskMapper.selectStatisticsCount(query);
    }

    @Override
    public SysServiceTask selectByOrderId(Long orderId) {
        SysServiceTask sysServiceTask = sysServiceTaskMapper.selectByOrderId(orderId);
        return sysServiceTask;
    }
}
