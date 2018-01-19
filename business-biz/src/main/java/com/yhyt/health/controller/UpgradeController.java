package com.yhyt.health.controller;

import com.alibaba.fastjson.JSON;
import com.yhyt.health.model.SysUpgrade;
import com.yhyt.health.model.UpgradeQuery;
import com.yhyt.health.service.UpGradeService;
import com.yhyt.health.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UpgradeController {
    private static Logger logger = LoggerFactory.getLogger(UpgradeController.class);
    @Autowired
    private UpGradeService upGradeService;

    @GetMapping("/upgrade/get/{id}")
    public SysUpgrade getUpGrade(@PathVariable("id")long id){
        return upGradeService.selectById(id);
    }

    @PostMapping("/upgrade/add")
    public int addUpGrade(@RequestBody SysUpgrade sysUpgrade){
        logger.info("发布升级，params:{}",JSON.toJSONString(sysUpgrade));
        return upGradeService.insert(sysUpgrade);
    }

    @PutMapping("/upgrade/update")
    public int updateUpGrade(@RequestBody SysUpgrade sysUpgrade){
        return upGradeService.update(sysUpgrade);
    }

    @GetMapping("/upgrade/list")
    public Page<SysUpgrade> queryUpGradeListPage(@RequestParam("queryStr") String queryStr){
        UpgradeQuery upgradeQuery = JSON.parseObject(queryStr, UpgradeQuery.class);
        return upGradeService.getSysUpGradeListPage(upgradeQuery);
    }
}
