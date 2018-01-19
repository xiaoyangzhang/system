package com.yhyt.health.model;

import org.springframework.stereotype.Component;

/**
 * @author wangzhan@cis.com.cn
 * @version v1.0
 * @project business-client
 * @Description
 * @encoding UTF-8
 * @date 2018/1/2
 * @time 下午2:30
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 *     v1.0 商品分类的枚举对应的实体对象
 * --------------------------------------------------
 * </pre>
 */
@Component
public class ItemClassification {

    /**
     * 商品分类id
     */
    private   int id;

    /**
     * 商品编码
     */
    private  String code;

    /**
     * 商品标题
     */
    private  String title;

    /**
     * 商品分类id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * 商品编码
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * 商品标题
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * 商品分类id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 商品编码
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 商品标题
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
