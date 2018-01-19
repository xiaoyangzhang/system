package com.yhyt.health.service;

import com.alibaba.fastjson.JSON;
import com.yhyt.health.config.PathConfiguration;
import com.yhyt.health.spring.AppResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shensh on 2017/9/12.
 */

@Service
public class MessageService {

    @Resource(name = "loadBalanced")
    private RestTemplate restTemplate;
    @Autowired
    private PathConfiguration pathConfiguration;

    private static Logger logger = LoggerFactory.getLogger(MessageService.class);
//    private static final String pathConfiguration.getDialogUrl()="http://dialog-zxy/";

    private void sendMsg(Long receiverId, String receiverType, String title, String content, String messageType) {
        //userType(接收类型) 1:医生、2:患者
        //messageType 1:系统消息;
        AppResult appResult = restTemplate.postForObject(pathConfiguration.getDialogUrl()+"/dialog/sendmessage?userId={1}&userType={2}&messageTitle={3}&messageContent={4}&messageType={5}", null, AppResult.class, receiverId, receiverType, title, content, messageType);
        logger.info("send message result,result:{}", JSON.toJSONString(appResult));
        if (appResult != null && !"200".equals(appResult.getStatus().getCode())) {
            logger.error("receiverId : " + receiverId + "title : " + title + "content : " + content + " 发送失败!");
        }
    }

    private void sendMsg(List<Long> receiverIds, String receiverType, String title, String content, String messageType) {
        //userType(接收类型) 1:医生、2:患者
        //messageType 1:系统消息;
        AppResult result = new AppResult();
//        result.setResultCode(ResultCode.SUCCESS);
        result.setCode("200");
        result.setMsg("SUCCESS");
        if (receiverIds != null && receiverIds.size() > 0) {
            for (Long receiverId : receiverIds) {
                AppResult appResult = restTemplate.postForObject(pathConfiguration.getDialogUrl()+"/dialog/sendmessage?userId={1}&userType={2}&messageTitle={3}&messageContent={4}&messageType={5}", null, AppResult.class, receiverId, receiverType, title, content, messageType);
                if (appResult != null && !"200".equals(appResult.getStatus().getCode())) {
                    logger.error("receiverId : " + receiverId + "title : " + title + "content : " + content + " 发送失败!");
                }
            }
        }
    }

    private void sendMsg(Long receiverId, String receiverType, String title, String content, String messageType,String operId) {
        //userType(接收类型) 1:医生、2:患者
        //messageType 1:系统消息;
        AppResult appResult = restTemplate.postForObject(pathConfiguration.getDialogUrl()+"/dialog/sendmessage?userId={1}&userType={2}&messageTitle={3}&messageContent={4}&messageType={5}&operId={6}", null, AppResult.class, receiverId, receiverType, title, content, messageType,operId);
        logger.info("send message result,result:{}", JSON.toJSONString(appResult));
        if (appResult != null && !"200".equals(appResult.getStatus().getCode())) {
            logger.error("receiverId : " + receiverId + "title : " + title + "content : " + content + " 发送失败!");
        }
    }



    @Async
    public void sendMessage(Long receiverId, String receiverType, String title, String content) {
        //messageType 1:系统消息;
        logger.info("send message to user,uesrId={}", receiverId, ",userType={}",
                receiverType.equals("1") ? "医生" : "患者", ",title={}", title, "content={}", content, "messageType={}", "1");
        sendMsg(receiverId, receiverType, title, content, "1");
    }

    @Async
    public void sendMessage(Long receiverId, String receiverType, String title, String content, String messageType) {
        //messageType 1:系统消息;
        logger.info("send message to user,uesrId={}", receiverId, ",userType={}",
                receiverType.equals("1") ? "医生" : "患者", ",title={}", title, "content={}", content, "messageType={}", messageType);
        String type = StringUtils.isBlank(messageType) ? "1" : messageType;
        sendMsg(receiverId, receiverType, title, content, type);
    }

    @Async
    public void sendMessage(List<Long> receiverIds, String receiverType, String title, String content) {
        //messageType 1:系统消息;
        logger.info("send message to user,uesrIds={}", receiverIds.toString(), ",userType={}",
                receiverType.equals("1") ? "医生" : "患者", ",title={}", title, "content={}", content, "messageType={}", "1");
        sendMsg(receiverIds, receiverType, title, content, "1");
    }


    @Async
    public void sendMessage(List<Long> receiverIds, String receiverType, String title, String content, String messageType) {
        //messageType 1:系统消息;
        logger.info("send message to user,uesrIds={}", receiverIds.toString(), ",userType={}",
                receiverType.equals("1") ? "医生" : "患者", ",title={}", title, "content={}", content, "messageType={}", messageType);
        String type = StringUtils.isBlank(messageType) ? "1" : messageType;
        sendMsg(receiverIds, receiverType, title, content, type);
    }

    @Async
    public void sendMessage(Long receiverId, String receiverType, String title, String content, String messageType,String operId) {
        //messageType 1:系统消息;
        logger.info("send message to user,uesrId={}", receiverId, ",userType={}",
                receiverType.equals("1") ? "医生" : "患者", ",title={}", title, "content={}", content, "messageType={}", "1");
        sendMsg(receiverId, receiverType, title, content, "1",operId);
    }


}
