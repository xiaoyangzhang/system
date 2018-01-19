package com.yhyt.health.service;

import java.util.List;

import com.yhyt.health.model.Dictionary;
import com.yhyt.health.model.vo.DictionaryVo;
import com.yhyt.health.spring.AppResult;

public interface DictionaryService {

	List<Dictionary> findDictionaryList(Dictionary dictionary);

	Dictionary editDictionary(Dictionary dictionary);

	void delete(Long id);

	AppResult getDictionaryByDictCode(String dictCode);

}
