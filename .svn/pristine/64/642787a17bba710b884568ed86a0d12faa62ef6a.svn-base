package com.yhyt.health.dao;

import com.yhyt.health.model.ItemPicture;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangzhan@cis.com.cn
 * @version v1.0
 * @project business-biz
 * @Description
 * @encoding UTF-8
 * @date 2018/1/5
 * @time 上午11:28
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 *     v1.0 商品图片mapper
 * --------------------------------------------------
 * </pre>
 */
@Repository
public interface ItemPictureMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ItemPicture record);

    int insertSelective(ItemPicture record);

    ItemPicture selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItemPicture record);

    int updateByPrimaryKey(ItemPicture record);

    /**
     * 根据指定字段删除数据
     * @param whereField
     * @param whereFieldValue
     * @return
     */
    int deleteByAppointField(final @Param("whereField") String whereField,
                             final @Param("whereFieldValue") Object whereFieldValue);

    /**
     * 根据商品id获取商品图片列表
     * @param itemId
     * @return
     */
    List<String> getPictureUrls(final @Param("itemId") Long itemId);
}
