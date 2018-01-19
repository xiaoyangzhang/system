package com.yhyt.health.controller;

import com.yhyt.health.model.SysBlacklist;
import com.yhyt.health.service.SysBlackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@ApiIgnore
public class SysBlackController {

    private static Logger logger = LoggerFactory.getLogger(SysBlackController.class);

    @Autowired
    private SysBlackService sysBlackService;

    @GetMapping("/getBlackList/{id}/{userType}")
    public List<SysBlacklist> getBlackListByIdAndType(@PathVariable("id") long id,@PathVariable("userType")long userType) {
        return sysBlackService.getSysBlacksByUserId(id,userType);
    }

    //TODO 参数传递

    /**
     * 添加或移除
     * @param sysBlacklist
     * @return
     */
    @PostMapping("/addBlacklist")
    public SysBlacklist addBlacklist(@RequestBody SysBlacklist sysBlacklist) {
        return sysBlackService.addBlacklist(sysBlacklist);
    }

    @GetMapping("/query/{id}")
    public SysBlacklist queryById(@PathVariable("id")long id){
        return sysBlackService.selectById(id);
    }
}
