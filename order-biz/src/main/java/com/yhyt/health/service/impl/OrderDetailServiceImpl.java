package com.yhyt.health.service.impl;

import com.yhyt.health.contans.ResultCode;
import com.yhyt.health.dao.OrderDetailMapper;
import com.yhyt.health.dao.OrderMapper;
import com.yhyt.health.model.Order;
import com.yhyt.health.model.OrderDetail;
import com.yhyt.health.result.AppResult;
import com.yhyt.health.result.ResultStatus;
import com.yhyt.health.service.OrderDetailService;
import com.yhyt.health.util.XALock;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

/**
 * Created by localadmin on 17/9/1.
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private static final byte UN_USED = 1;
    private static final byte USED = 2;
    private static final byte INVALID = 3;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public AppResult getPatientOrderDetail(String patientId) {
        AppResult appResult = new AppResult();
        Map<String, Object> criterias = new HashMap<>();
        criterias.put("patientId", patientId);
        List<OrderDetail> orderDetails = orderDetailMapper.getUserOrderDetails(criterias);
        Map<String, Object> data = new HashMap<>();
        if (!orderDetails.isEmpty()) {
            Map<String, List<OrderDetail>> typedOrderDetails = orderDetails.stream().filter(r -> r.getType() == 1 || r.getType() == 2 || r.getType() == 3
                    || r.getType() == 4).collect(Collectors.groupingBy(r -> orderDetailType.valueOf(r.getType()).toString()));
            for (Map.Entry<String, List<OrderDetail>> stringListEntry : typedOrderDetails.entrySet()) {
                data.put(stringListEntry.getKey(), stringListEntry.getValue());
            }
        }
        appResult.setBody(data);
        ResultStatus resultStatus = new ResultStatus(ResultCode.SUCCESS.val(), ResultCode.SUCCESS.msg());
        appResult.setStatus(resultStatus);
        return appResult;
    }

    @Override
    public AppResult markCardUsed(long patientId, long departId) {
        AppResult appResult = new AppResult();
        Map<String, Object> criterias = new HashMap<>();
        criterias.put("patientId", patientId);
        criterias.put("type", 2);
        criterias.put("departId", departId);
        List<OrderDetail> cards = orderDetailMapper.getUserOrderDetails(criterias);
        if (cards.isEmpty()) {
            appResult.setStatus(new ResultStatus("201", "用户聊天状态异常"));
            return appResult;
        }
        OrderDetail orderDetail = cards.get(0);
        orderDetail.setType((byte) 3);

        orderDetailMapper.updateByPrimaryKey(orderDetail);
        appResult.setStatus(new ResultStatus(ResultCode.SUCCESS.val(), ResultCode.SUCCESS.msg()));
        return appResult;
    }

    @Override
    @Transactional
    public AppResult useCard(long patientId, long departId) throws Exception {
        AppResult appResult = new AppResult();
        /*final String globalCardKey = patientId+"";
        long lockTime = 5000;
        String holderKey = XALock.lock(globalCardKey,lockTime);//单点登录，不需要分布式保证
        if(holderKey == null){
            //失败直接返回
            appResult.setStatus(new ResultStatus("201","用卡失败，请稍后再试"));
            return appResult;
        }else {*/
        long beginTime = System.currentTimeMillis();
        Map<String, Object> criterias = new HashMap<>();
        criterias.put("patientId", patientId);
        criterias.put("type", 1);
        List<OrderDetail> cards = orderDetailMapper.getUserOrderDetails(criterias);
        if (!cards.isEmpty()) {
            OrderDetail card = cards.get(0);
            card.setType((byte) 2);
            card.setDepartId(departId);
            orderDetailMapper.updateByPrimaryKey(card);
            Order order = orderMapper.selectByPrimaryKey(card.getOderId());
//            order.setRemainCount(order.getRemainCount() - 1);
            if(null!=order) {
            Order order1 = new Order();
            order1.setId(order.getId());
            order1.setRemainCount(order.getRemainCount() - 1);
            orderMapper.updateByPrimaryKeySelective(order1);
            }
            appResult.setStatus(new ResultStatus(ResultCode.SUCCESS.val(), ResultCode.SUCCESS.msg()));
            appResult.getBody().put("cardId", card.getId());
           
        } else {
            appResult.setStatus(new ResultStatus("201", "没有卡可用"));
        }
        long execTime = System.currentTimeMillis() - beginTime;
        logger.debug("执行耗时{}毫秒", execTime);
            /*if(execTime > lockTime){
                throw new TimeoutException();
            }
            XALock.unlock(globalCardKey,holderKey);
        }*/

        return appResult;
    }

    @Override
    public AppResult cancelUseCard(long patientId, long departId) {
        AppResult appResult = new AppResult();
        if (updateCardUseStatus(patientId, departId, (byte) 1))
            appResult.setStatus(new ResultStatus(ResultCode.SUCCESS.val(), ResultCode.SUCCESS.msg()));
        else
            appResult.setStatus(new ResultStatus("201", "用户聊天状态异常"));
        return appResult;
    }

    private boolean updateCardUseStatus(long patientId, long departId, byte status) {
        Map<String, Object> criterias = new HashMap<>();
        criterias.put("patientId", patientId);
        criterias.put("type", 2);
        criterias.put("departId", departId);
        List<OrderDetail> cards = orderDetailMapper.getUserOrderDetails(criterias);
        if (cards.isEmpty()) {
//            appResult.setStatus(new ResultStatus("201", "用户聊天状态异常"));
            return false;
        }
        OrderDetail orderDetail = cards.get(0);
        orderDetail.setType(status);
        orderDetailMapper.updateByPrimaryKey(orderDetail);
        if (status == 1) {
        	if(null!=orderDetail.getOderId()) {
            Order order = orderMapper.selectByPrimaryKey(orderDetail.getOderId());
            order.setRemainCount(order.getRemainCount() + 1);
            orderMapper.updateByPrimaryKey(order);
        	}
        }
        return true;
    }

    private enum orderDetailType {
        UNUSED(1),
        USED(3),
        INVALID(4);
        private int value;

        private orderDetailType(int value) {
            this.value = value;
        }

        static orderDetailType valueOf(int value) {
            if (value == 1 || value == 2) {
                return UNUSED;
            } else if (value == 3) {
                return USED;
            } else if (value == 4) {
                return INVALID;
            }
            return null;
        }
    }

    @Override
    public OrderDetail getOrderDetail(String cardId){
        if(StringUtils.isBlank(cardId))
            throw new IllegalArgumentException(cardId);
        return orderDetailMapper.selectByPrimaryKey(Long.valueOf(cardId));
    }
}
