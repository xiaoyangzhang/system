package com.yhyt.health.controller;

import java.util.List;

import com.yhyt.health.spring.AppResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yhyt.health.model.Dictionary;
import com.yhyt.health.service.DictionaryService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RequestMapping("/dictionary")
@RestController
public class DictionaryController {
	
	@Autowired
	private DictionaryService dictionaryService;

	@ApiOperation(value="获取字典列表", notes="系统")
	@ApiImplicitParams({
    	@ApiImplicitParam(name = "dictCode", value = "字典分类编码",paramType="query", required = true, dataType = "String")
    })
	@GetMapping("/")
	public List<Dictionary> findDictionaryList(Dictionary dictionary){
		return this.dictionaryService.findDictionaryList(dictionary);
	}
	
	@ApiIgnore
	@RequestMapping("/{id}")
	public Dictionary findDictionary(Dictionary dictionary){
		List<Dictionary> result  = this.dictionaryService.findDictionaryList(dictionary);
		if(result==null||result.size()==0){
			return null;
		}
		return result.get(0);
	}
	
	@ApiIgnore
	@RequestMapping("/edit")
	public Dictionary editDictionary(Dictionary dictionary){
		return this.dictionaryService.editDictionary(dictionary);
	}
	
	@ApiIgnore
	@RequestMapping("/delete/{id}")
	public void deleteDictionary(Dictionary dictionary){
		this.dictionaryService.delete(dictionary.getId());
	}

	@GetMapping("/dictionary")
	public AppResult getDictionaryByDictCode(String dictCode) {
		return this.dictionaryService.getDictionaryByDictCode(dictCode);
	}
}
