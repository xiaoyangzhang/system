package com.yhyt.health.dao;

import com.yhyt.health.model.Dictionary;
import com.yhyt.health.model.vo.DictionaryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DictionaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Dictionary record);

    int insertSelective(Dictionary record);

    Dictionary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Dictionary record);

    int updateByPrimaryKey(Dictionary record);

	List<Dictionary> findDictionaryList(Map<String, Object> params);

    List<DictionaryVo> getDictionaryByDictCode(@Param("dictCode") String dictCode);
}