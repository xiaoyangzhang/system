package com.yhyt.health.service;

import com.yhyt.health.model.ItemClassification;

import java.util.List;

/**
 * 商品分类service
 */
public interface ItemClassificationService {

    /**
     * 获取商品分类列表
     *  目前暂时从枚举中获取
     * @return
     */
    public List<ItemClassification> getItemClassification();
}
