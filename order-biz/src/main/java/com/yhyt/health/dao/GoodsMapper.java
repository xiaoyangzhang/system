package com.yhyt.health.dao;

import com.yhyt.health.model.Goods;
import org.springframework.stereotype.Repository;

/**
 * Created by localadmin on 17/9/1.
 */
@Repository
public interface GoodsMapper {
    Goods selectByPrimaryKey(long goodsId);

    Goods selectItemByPrimaryKey(Long itemId);
}
