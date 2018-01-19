package com.yhyt.health.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
//@ConfigurationProperties(prefix = "url")
public class PathConfiguration {

    @Value("${url.dialogUrl}")
    private String dialogUrl;
    @Value("${url.systemUrl}")
    private String systemUrl;
    @Value("${url.patientUrl}")
    private String patientUrl;
    @Value("${url.gateWay}")
    private String gateWay;
//    @Value("${url.systemUrl}")
//    private String systemUrl;

    public String getGateWay() {
        return gateWay;
    }

    public String getDialogUrl() {
        return dialogUrl;
    }

    public String getPatientUrl() {
        return patientUrl;
    }

    public String getSystemUrl() {
        return systemUrl;
    }

    @Bean
    public PathConfiguration getPathConfiguration(){
        PathConfiguration pathConfiguration = new PathConfiguration();
        pathConfiguration.dialogUrl=dialogUrl;
        pathConfiguration.patientUrl=patientUrl;
        pathConfiguration.gateWay=gateWay;
        pathConfiguration.systemUrl=systemUrl;
        return pathConfiguration;
    }
}
