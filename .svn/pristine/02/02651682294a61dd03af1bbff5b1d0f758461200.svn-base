package com.yhyt.health.controller;

import com.yhyt.health.model.SysLink;
import com.yhyt.health.service.SyslinkService;
import com.yhyt.health.util.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@RequestMapping("/syslink")
@RestController
@ApiIgnore
public class SyslinkController {

	@Autowired
	private SyslinkService syslinkService;
	
	@RequestMapping("/")
	public List<SysLink> findSyslinkList(SysLink syslink){
		return this.syslinkService.findPersistableList(syslink);
	}
	@RequestMapping("/edit")
	public SysLink editSysLink(SysLink syslink){

		return this.syslinkService.edit(syslink);
	}

	@ApiIgnore
	@RequestMapping("/page")
	public Page<SysLink> findSysLinkPageList(String name,Integer pageIndex, Integer pageSize){
		Map<String,Object> params = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(name)){
			params.put("code", name);
		}
		return this.syslinkService.findPersistableList(params, pageIndex,pageSize);
	}

	@ApiIgnore
	@GetMapping("/H5/get/{id}")
	public SysLink getH5ById(@PathVariable("id") Long id){
		return syslinkService.findPersistable(id);
	}

	@ApiIgnore
	@DeleteMapping("/H5/delete/{id}")
	public void deleteH5(@PathVariable("id")Long id){
		 syslinkService.delete(id);
	}


}
