//package com.yhyt.health.component;
//
//import org.springframework.scheduling.Trigger;
//import org.springframework.scheduling.TriggerContext;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//import org.springframework.scheduling.support.CronTrigger;
//import org.springframework.stereotype.Component;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@Component
//public class DynamicScheduledTask implements SchedulingConfigurer {
//    private static final SimpleDateFormat onShelf = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
//    private static final SimpleDateFormat offShelf = new SimpleDateFormat("yyyy-MM-dd HH:59:59");
//
//    private static final String DEFAULT_CRON = "0/5 * * * * ?";
//    private String cron = DEFAULT_CRON;
//
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        taskRegistrar.addTriggerTask(new Runnable() {
//            @Override
//            public void run() {
//                Date date = new Date();
//                String format1 = onShelf.format(date);
//                String format2 = offShelf.format(date);
//                //触发上架操作
//                if ("".equals(format1)){
//
//                }else if ("".equals(format2)){//触发下架操作
//
//                }
//            }
//        }, new Trigger() {
//            @Override
//            public Date nextExecutionTime(TriggerContext triggerContext) {
//                CronTrigger cronTrigger = new CronTrigger(cron);
//                Date nextExecutionTime = cronTrigger.nextExecutionTime(triggerContext);
//                return nextExecutionTime;
//            }
//        });
//    }
//
//    public void setCron(String cron) {
//        this.cron = cron;
//    }
//}
