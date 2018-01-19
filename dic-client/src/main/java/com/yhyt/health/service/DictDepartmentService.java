package com.yhyt.health.service;

import com.yhyt.health.model.DictDepartment;
import com.yhyt.health.model.DictDepartmentDTO;

import java.util.List;
import java.util.Map;

public interface DictDepartmentService {

    List<DictDepartment> findDictDepartmentList(Map<String, Object> params);

    /**
     * 根据主键集合查询
     * @param idList
     * @return
     */
    List<DictDepartment> findDictDepartmentList(String idStr);

    /**
     * 查询所有的一级科室
     * @return
     */
    List<DictDepartment> getLevelOneDepts();

    /**
     *
     * @return 所有的一级二级科室
     */
    List<DictDepartmentDTO> getDictDeptList();



}
