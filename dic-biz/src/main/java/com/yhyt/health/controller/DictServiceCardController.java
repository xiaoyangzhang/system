package com.yhyt.health.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yhyt.health.model.DictServiceCard;
import com.yhyt.health.service.DictServiceCardService;

import springfox.documentation.annotations.ApiIgnore;

@RequestMapping("/dictServiceCard")
@RestController
@ApiIgnore
public class DictServiceCardController {

	@Autowired
	private DictServiceCardService serviceCardService;
	
	@RequestMapping("/")
	public List<DictServiceCard> findDictServiceCardList(DictServiceCard serviceCard){
		return this.serviceCardService.findDictServiceCardList(serviceCard);
	}
	
	@RequestMapping("/edit")
	public DictServiceCard editDictServiceCard(DictServiceCard serviceCard){
		return this.serviceCardService.editDictServiceCard(serviceCard);
	}
}
