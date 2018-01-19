package com.yhyt.health.service;

import com.yhyt.health.model.SysServiceTask;
import com.yhyt.health.model.dto.CustomerServiceDTO;
import com.yhyt.health.model.dto.ServiceDetailDTO;
import com.yhyt.health.model.dto.TaskStatisticsDTO;
import com.yhyt.health.model.query.CustomerServiceQuery;
import com.yhyt.health.util.Page;

public interface CustomerService {

    /**
     * 客服任务列表
     * @param query
     * @return
     */
     Page<CustomerServiceDTO> selectServiceListPage(CustomerServiceQuery query);

    /**
     * 客服任务详情
     * @param id
     * @return
     */
     ServiceDetailDTO selectById(Long id);

    /**
     * 一键释放
     * @param operator
     * @return
     */
    int releaseTaskByOperator(String operator);

    /**
     * 跟进
     * @param id
     * @return
     */
    int updateTaskState(Long id,Long orderId,Byte state,String content,String operator,String action);

    /**
     * 客服任务列表统计
     * @param query
     * @return
     */
    TaskStatisticsDTO selectStatisticsCount(CustomerServiceQuery query);


    SysServiceTask selectByOrderId(Long orderId);


}
