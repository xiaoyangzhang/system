package com.yhyt.health.service.impl;

import com.yhyt.health.model.ItemClassification;
import com.yhyt.health.model.enumE.ItemClassificationEnum;
import com.yhyt.health.service.ItemClassificationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 商品分类serviceImpl
 */
@Service
public class ItemClassificationServiceImpl implements ItemClassificationService {

    @Override
    public List<ItemClassification> getItemClassification() {
        /**
         * 从ItemClassificationEnum枚举类中获取商品分类列表
         */
        List<ItemClassification> list =  new ArrayList<>();
        ConcurrentHashMap<Byte,ItemClassificationEnum> map = ItemClassificationEnum.map;
        for (Map.Entry<Byte, ItemClassificationEnum> entry : map.entrySet()) {
            ItemClassificationEnum itemClassificationEnum  = entry.getValue();
            ItemClassification vo = new ItemClassification();
            vo.setId(itemClassificationEnum.getId());
            vo.setCode(itemClassificationEnum.getCode());
            vo.setTitle(itemClassificationEnum.getTitle());
            list.add(vo);
        }
        return list;
    }
}
