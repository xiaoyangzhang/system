package com.yhyt.health.controller;

import com.yhyt.health.model.DictDepartment;
import com.yhyt.health.model.DictDepartmentDTO;
import com.yhyt.health.service.DictDepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DictDepartmentController {

    private static Logger logger = LoggerFactory.getLogger(DictDepartmentController.class);

    @Autowired
    private DictDepartmentService dictDepartmentService;
    
    @ApiIgnore
    @GetMapping("/dictDepartmentList/{parentCode}")
    public List<DictDepartment> getDictDepartmentList(@PathVariable("parentCode")Integer code) {
        Map<String, Object> params = new HashMap<>();
        params.put("parentCode",code);
        return dictDepartmentService.findDictDepartmentList(params);
    }

    @ApiIgnore
    @GetMapping("/levelOneDepts/query")
    public List<DictDepartment> queryLevelOneDepts() {
        return dictDepartmentService.getLevelOneDepts();
    }

    @GetMapping("/dictDepartmentListByIds/{idStr}")
    public List<DictDepartment> getDictDepartmentList(@PathVariable("idStr")String idStr){
//        List<Long> idList = JSON.parseArray(idStr, Long.class);
        return dictDepartmentService.findDictDepartmentList(idStr);
    }

    @GetMapping("/dictDeptList/query")
    public List<DictDepartmentDTO> queryDictDeptList(){
        return dictDepartmentService.getDictDeptList();
    }
}
