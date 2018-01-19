package com.yhyt.health.Schedule;

import com.yhyt.health.constant.Constant;
import com.yhyt.health.service.ItemService;
import com.yhyt.health.spring.AppResult;
import com.yhyt.health.util.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gsh
 * @create 2017-12-11 10:10
 **/
@Component("quartzTask")
public class QuartzTask {
    static final Logger logger = LoggerFactory.getLogger(QuartzTask.class);

    @Autowired
    @Qualifier("loadBalanced")
    private RestTemplate restTemplate;

    @Autowired
    private ItemService itemService;

    //医略定时任务
    public void articleTask() {
        logger.info("开始执行医略定时任务");
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
            AppResult appResult = this.restTemplate.postForObject(Constant.DOCTOR_SERVICE + "/schedule/update/state", httpEntity, AppResult.class);
            if ("200".equals(appResult.getStatus().getCode())) {
                logger.info("医略定时任务执行完毕");
            } else {
                logger.info("医略定时任务执行失败");
                throw new BusinessException("401","产科定时任务失败");
            }
        } catch (Exception e) {
            logger.error("执行医略定时任务出现错误",e);
            throw new BusinessException("401","产科定时任务失败");
        }

    }

    //诊后随访定时任务
    public void ClinicFollowUp(){
        logger.info("开始执行诊后随访定时任务");
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
            AppResult appResult = this.restTemplate.getForObject(Constant.DIALOG_SERVICE + "/dialog/overtime", AppResult.class);
            if ("200".equals(appResult.getStatus().getCode())) {
                logger.info("诊后随访定时任务执行完毕");
            } else {
                logger.info("诊后随访定时任务执行失败");
                throw new BusinessException("401","产科定时任务失败");
            }
        } catch (Exception e) {
            logger.error("执行诊后随访定时任务出现错误",e);
            throw new BusinessException("401","产科定时任务失败");
        }
    }

    //医略定时任务
    public void sendEmailOnTime(){
        logger.info("开始执行产科定时任务");
        try {
            Map<String, Object> mapPara = new HashMap<String, Object>();
            AppResult appResult
                    =restTemplate.getForObject(Constant.DOCTOR_SERVICE+"/getObstetricsDetailListMail",AppResult.class, mapPara);
            if ("200".equals(appResult.getStatus().getCode())) {
                logger.info("产科定时任务执行完毕");
            } else {
                logger.info("产科定时任务执行失败");
                throw new BusinessException("401","产科定时任务失败");
            }
        } catch (Exception e) {
            logger.error("执行产科定时任务出现错误",e);
            throw new BusinessException("401","产科定时任务失败");
        }
    }

    /**
     * 新健康（预约提醒接口）
     * date : 2017/12/29
     */
    public void reservationReminding(){
        logger.info("开始执行定时任务每天晚间9点发送预约提醒");
        try {
            Map<String, Object> mapPara = new HashMap<String, Object>();
            AppResult appResult
                    =restTemplate.getForObject(Constant.DOCTOR_SERVICE+"/reservationReminding",AppResult.class, mapPara);
            if ("200".equals(appResult.getStatus().getCode())) {
                logger.info("定时任务每天晚间9点发送预约提醒执行完毕");
            } else {
                logger.info("定时任务每天晚间9点发送预约提醒执行失败");
                throw new BusinessException("401","定时任务每天晚间9点发送预约提醒执行失败");
            }
        } catch (Exception e) {
            logger.error("定时任务每天晚间9点发送预约提醒执行出现错误",e);
            throw new BusinessException("401","定时任务每天晚间9点发送预约提醒执行失败");
        }
    }

    /**
     * 预约过期定时任务
     * date : 2017/12/29
     */
    public void overdueUndiagnose(){
        logger.info("开始执行定时任务预约过期");
        try {
            Map<String, Object> mapPara = new HashMap<String, Object>();
            AppResult appResult
                    =restTemplate.getForObject(Constant.DOCTOR_SERVICE+"/overdueUndiagnose",AppResult.class, mapPara);
            if ("200".equals(appResult.getStatus().getCode())) {
                logger.info("定时任务预约过期执行完毕");
            } else {
                logger.info("定时任务预约过期执行失败");
                throw new BusinessException("401","定时任务预约过期执行失败");
            }
        } catch (Exception e) {
            logger.error("定时任务预约过期执行出现错误",e);
            throw new BusinessException("401","定时任务预约过期执行失败");
        }
    }

    /**
     * 商品上下架
     */
    public void itemStateChange() {
        logger.info("改变商品上下架定时任务");
        try {
            itemService.itemStateChange();
        } catch (Exception e) {
            logger.error("改变商品上下架定时任务执行出现错误",e);
            throw new BusinessException("401","改变商品上下架定时任务执行失败");
        }
    }
}
