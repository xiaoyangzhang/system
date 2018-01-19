package com.yhyt.health.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangzhan@cis.com.cn
 * @version v1.0
 * @project business-biz
 * @Description
 * @encoding UTF-8
 * @date 2018/1/5
 * @time 上午11:02
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 *     v1.0 商品图片
 * --------------------------------------------------
 * </pre>
 */
public class ItemPicture implements Serializable{

    /**
     *  图片id
     */
    private Long id;

    /**
     * 商品id
     */
    private Long itemId;

    /**
     * 图片url
     */
    private String url;

    /**
     * 图片排序位置
     */
    private Byte sortNumber;

    /**
     *
     */
    private Date createTime;

    /**
     * 图片id
     * @return
     */
    public Long getId() { return id; }

    /**
     * 图片id
     * @param id
     */
    public void setId(Long id) { this.id = id; }

    /**
     * 商品id
     * @return
     */
    public Long getItemId() { return itemId; }

    /**
     * 商品id
     * @param itemId
     */
    public void setItemId(Long itemId) { this.itemId = itemId; }

    /**
     * 图片url
     * @return
     */
    public String getUrl() { return url; }

    /**
     * 图片url
     * @param url
     */
    public void setUrl(String url) { this.url = url; }

    /**
     * 图片排序位置
     * @return
     */
    public Byte getSortNumber() { return sortNumber; }

    /**
     * 图片排序位置
     * @param sortNumber
     */
    public void setSortNumber(Byte sortNumber) { this.sortNumber = sortNumber; }

    /**
     *
     * @return
     */
    public Date getCreateTime() { return createTime; }

    /**
     *
     * @param createTime
     */
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
}
