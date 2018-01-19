package com.yhyt.health.dao;

import com.yhyt.health.model.DictDepartment;
import com.yhyt.health.model.DictDepartmentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DictDepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DictDepartment record);

    int insertSelective(DictDepartment record);

    DictDepartment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DictDepartment record);

    int updateByPrimaryKey(DictDepartment record);

    List<DictDepartment> getDeptCategoryByLevel(Byte level,Long parentId);

	List<DictDepartment> findDictDepartmentList(Map<String, Object> params);
	List<DictDepartmentVO> getDictDepartmentList(Map<String, Object> params);
	List<DictDepartment> getLevelOneDepts();
	List<DictDepartment> getDictDeptList(@Param("idStr")String idStr);

}