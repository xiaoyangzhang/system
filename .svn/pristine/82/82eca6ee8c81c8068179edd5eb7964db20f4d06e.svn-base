package com.yhyt.health.service.impl;

import com.yhyt.health.dao.ItemPictureMapper;
import com.yhyt.health.model.ItemPicture;
import com.yhyt.health.service.ItemPictureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wangzhan@cis.com.cn
 * @version v1.0
 * @project business-biz
 * @Description
 * @encoding UTF-8
 * @date 2018/1/5
 * @time 上午11:25
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 *     v1.0 商品图片serviceImpl
 * --------------------------------------------------
 * </pre>
 */
@Service
public class ItemPictureServiceImpl implements ItemPictureService {

    @Resource
    private ItemPictureMapper itemPictureMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return itemPictureMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ItemPicture record) { return itemPictureMapper.insert(record); }

    @Override
    public int insertSelective(ItemPicture record) {
        return itemPictureMapper.insertSelective(record);
    }

    @Override
    public ItemPicture selectByPrimaryKey(Long id) {
        return itemPictureMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ItemPicture record) {
        return itemPictureMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ItemPicture record) {
        return itemPictureMapper.updateByPrimaryKey(record);
    }
}
