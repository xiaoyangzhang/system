package com.yhyt.health.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yhyt.health.model.vo.DictionaryVo;
import com.yhyt.health.spring.AppResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhyt.health.dao.DictionaryMapper;
import com.yhyt.health.model.Dictionary;
import com.yhyt.health.service.DictionaryService;

@Service
public class DictionaryServiceImpl implements DictionaryService {

	@Autowired
	private DictionaryMapper dictionaryMapper;
	
	@Override
	public List<Dictionary> findDictionaryList(Dictionary dictionary) {
		Map<String,Object> params = new HashMap<String,Object>();
		if(dictionary!=null){
			if(dictionary.getId()!=null){
				params.put("id", dictionary.getId());
			}
			if(dictionary.getDictCode()!=null){
				params.put("dictCode", dictionary.getDictCode());
			}
			if(StringUtils.isNotBlank(dictionary.getDictName())){
				params.put("dictName", dictionary.getDictName());
			}
			if(StringUtils.isNotBlank(dictionary.getItemName())){
				params.put("itemName", dictionary.getItemName());
			}			
		}
		return this.dictionaryMapper.findDictionaryList(params);
	}

	@Override
	public Dictionary editDictionary(Dictionary dictionary) {
		if(dictionary!=null){
			Dictionary newDictionary = null;
			if(dictionary.getId()!=null){
				newDictionary = this.dictionaryMapper.selectByPrimaryKey(dictionary.getId());
			}else{
				newDictionary = new Dictionary();
			}
			BeanUtils.copyProperties(dictionary, newDictionary);
			if(dictionary.getId()==null){
				this.dictionaryMapper.insert(newDictionary);
				dictionary.setId(newDictionary.getId());
			}else{
				this.dictionaryMapper.updateByPrimaryKeySelective(newDictionary);
			}
		}
		return dictionary;
	}

	@Override
	public void delete(Long id) {
		this.dictionaryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public AppResult getDictionaryByDictCode(String dictCode) {
		AppResult appResult = new AppResult();
		List<DictionaryVo> dictionaryVos = dictionaryMapper.getDictionaryByDictCode(dictCode);
		appResult.setBody(dictionaryVos);
		return appResult;
	}
}
