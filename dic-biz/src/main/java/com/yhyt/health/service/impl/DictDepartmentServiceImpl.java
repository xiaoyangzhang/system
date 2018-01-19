package com.yhyt.health.service.impl;

import com.yhyt.health.dao.DictDepartmentMapper;
import com.yhyt.health.model.DictDepartment;
import com.yhyt.health.model.DictDepartmentDTO;
import com.yhyt.health.model.DictDepartmentVO;
import com.yhyt.health.service.DictDepartmentService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DictDepartmentServiceImpl implements DictDepartmentService {


    @Autowired
    private DictDepartmentMapper dictDepartmentMapper;
    @Override
    public List<DictDepartment> findDictDepartmentList(Map<String, Object> params) {
        return dictDepartmentMapper.findDictDepartmentList(params);
    }

    @Override
    public List<DictDepartment> findDictDepartmentList(String idStr) {
        if (StringUtils.isEmpty(idStr) ){
            return Collections.emptyList();
        }
        return dictDepartmentMapper.getDictDeptList(idStr);
    }

    @Override
    public List<DictDepartment> getLevelOneDepts() {
        return dictDepartmentMapper.getLevelOneDepts();
    }

    @Override
    public List<DictDepartmentDTO> getDictDeptList() {
        List<DictDepartment> levelOneDepts = getLevelOneDepts();
        ArrayList<DictDepartmentDTO> dictDepartmentDTOS = new ArrayList<>();
        HashMap<String, Object> params = new HashMap<>();
        for (DictDepartment dd:levelOneDepts){
            DictDepartmentDTO dictDepartmentDTO = new DictDepartmentDTO();
            dictDepartmentDTO.setLevelOneName(dd.getParentName());
            params.put("parentCode",dd.getParentCode());
            List<DictDepartmentVO> dictDepartmentList = dictDepartmentMapper.getDictDepartmentList(params);
            dictDepartmentDTO.setLevelTwoList(dictDepartmentList);
            dictDepartmentDTOS.add(dictDepartmentDTO);
        }
//        DictDepartmentDTO dictDepartmentDTO = new DictDepartmentDTO();
//        dictDepartmentDTO.setLevelOneName("通用");
//        dictDepartment.setParentCode(-1);
//        levelOneDepts.add(dictDepartment);
//        dictDepartment.setParentName("通用");
//        dictDepartment.setId(-1L);
//        dictDepartmentDTOS.get.add(dictDepartment);
        return dictDepartmentDTOS;
    }
}
