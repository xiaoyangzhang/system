package com.yhyt.health.service.impl;

import com.yhyt.health.dao.QuartzJobMapper;
import com.yhyt.health.model.QuartzJob;
import com.yhyt.health.quartz.ScheduleUtils;
import com.yhyt.health.service.QuartzService;
import org.quartz.CronTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author gsh
 * @create 2017-12-05 10:18
 **/
@Service
public class QuartzServiceImpl implements QuartzService{

    @Autowired
    private QuartzJobMapper quartzJobMapper;

    @PostConstruct
    public void init() {
        List<QuartzJob> jobList = quartzJobMapper.list();
        for(QuartzJob job : jobList) {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(job.getJobId());
            //如果不存在，则创建
            if(cronTrigger == null) {
                ScheduleUtils.createScheduleJob(job);
            }else {
                ScheduleUtils.updateScheduleJob(job);
            }
        }
    }
}
