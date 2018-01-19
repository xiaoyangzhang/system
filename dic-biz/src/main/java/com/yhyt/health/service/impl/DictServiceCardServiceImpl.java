package com.yhyt.health.service.impl;

import com.yhyt.health.dao.DictServiceCardMapper;
import com.yhyt.health.model.DictServiceCard;
import com.yhyt.health.service.DictServiceCardService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DictServiceCardServiceImpl implements DictServiceCardService{

	@Autowired
	private DictServiceCardMapper serviceCardMapper;

	@Override
	public List<DictServiceCard> findDictServiceCardList(DictServiceCard serviceCard) {
		Map<String,Object> params = new HashMap<String,Object>();
		if(serviceCard!=null){
			if(StringUtils.isNotBlank(serviceCard.getName())){
				params.put("name", serviceCard.getName());
			}
			if(serviceCard.getPrice()!=null){
				params.put("price", serviceCard.getPrice());
			}
			if(serviceCard.getCount()!=null){
				params.put("Count", serviceCard.getCount());
			}
		}
		return this.serviceCardMapper.findDictServiceCardList(params);
	}

	@Override
	public DictServiceCard editDictServiceCard(DictServiceCard serviceCard) {
		if(serviceCard!=null){
			DictServiceCard newServiceCard = null;
			if(serviceCard.getId()!=null){
				newServiceCard = this.serviceCardMapper.selectByPrimaryKey(serviceCard.getId());
			}else{
				newServiceCard = new DictServiceCard();
			}
			BeanUtils.copyProperties(serviceCard, newServiceCard);
			if(serviceCard.getId()==null){
				this.serviceCardMapper.insertSelective(newServiceCard);
				serviceCard.setId(newServiceCard.getId());
			}else{
				this.serviceCardMapper.updateByPrimaryKeySelective(newServiceCard);
			}
		}
		return serviceCard;
	}
}
