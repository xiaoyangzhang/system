package com.yhyt.health.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;

//@Component
public class ItemResultDTO implements Serializable {

    /**
     * 商品id
     */
    private Long id;

    /**
     * 商品编号
     */
    private String code;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品分类
     */
    private String categoryName;

    /**
     * 所属医院名称
     */
    private String hostitalName;

    /**
     * 所属科室名称
     */
    private String departmentName;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 置顶排序
     */
    private String topOrder;

    /**
     * 商品状态name (1:待处理 2:上架 3:处理中 4:下架)
     */
    private String stateName;

    /**
     * 商品状态code (1:待处理 2:上架 3:处理中 4:下架)
     */
    private Byte state;

    /**
     * 创建人
     */
    private String createOperator;

    /**
     * 上架时间
     */
    private String  onshelfDay;

    /**
     * 下架时间
     */
    private String offshelfDay;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 商品id
     * @return
     */
    public Long getId() { return id; }

    /**
     * 商品编码
     * @return
     */
    public String getCode() { return code; }

    /**
     * 商品名称
     * @return
     */
    public String getName() { return name;}

    /**
     * 商品价格
     * @return
     */
    public BigDecimal getPrice() { return price; }

    /**
     * 商品分类
     * @return
     */
    public String getCategoryName() {  return categoryName; }

    /**
     * 所属医院
     * @return
     */
    public String getHostitalName() { return hostitalName; }

    /**
     * 所属科室
     * @return
     */
    public String getDepartmentName() { return departmentName; }

    /**
     * 商品描述
     * @return
     */
    public String getDescription() { return description; }

    /**
     * 置顶
     * @return
     */
    public String getTopOrder() { return topOrder; }

    /**
     * 状态name
     * @return
     */
    public String getStateName() { return stateName; }

    /**
     * 状态code
     * @return
     */
    public Byte getState() { return state; }

    /**
     * 操作者
     * @return
     */
    public String getCreateOperator() { return createOperator; }

    /**
     * 上架时间
     * @return
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")

    public String getOnshelfDay() { return onshelfDay; }

    /**
     * 下架时间
     * @return
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")

    public String getOffshelfDay() { return offshelfDay; }

    /**
     * 商品id
     * @param id
     */
    public void setId(Long id) { this.id = id; }

    /**
     * 商品编码
     * @param code
     */
    public void setCode(String code) {  this.code = code; }

    /**
     * 商品名称
     * @param name
     */
    public void setName(String name) { this.name = name; }

    /**
     * 商品价格
     * @param price
     */
    public void setPrice(BigDecimal price) { this.price = price; }

    /**
     * 商品类别
     * @param categoryName
     */
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    /**
     * 所属医院名称
     * @param hostitalName
     */
    public void setHostitalName(String hostitalName) { this.hostitalName = hostitalName; }

    /**
     * 所属科室名称
     * @param departmentName
     */
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    /**
     * 商品描述
     * @param description
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * 置顶
     * @param topOrder
     */
    public void setTopOrder(String topOrder) { this.topOrder = topOrder; }

    /**
     * 状态名称
     * @param stateName
     */
    public void setStateName(String stateName) { this.stateName = stateName; }

    /**
     * 状态code
     * @param state
     */
    public void setState(Byte state) { this.state = state; }

    /**
     * 操作者
     * @param createOperator
     */
    public void setCreateOperator(String createOperator) {  this.createOperator = createOperator; }

    /**
     * 上架时间
     * @param onshelfDay
     */
    public void setOnshelfDay(String onshelfDay) { this.onshelfDay = onshelfDay; }

    /**
     * 下架时间
     * @param offshelfDay
     */
    public void setOffshelfDay(String offshelfDay) { this.offshelfDay = offshelfDay; }

    /**
     * 创建时间
     * @return
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")

    public String getCreateTime() { return createTime; }

    /**
     * 创建时间
     * @param createTime
     */
    public void setCreateTime(String createTime) { this.createTime = createTime; }

    /**
     * 页面返回商品id＋code
     * @return
     */
    public String codeId(){
        return "{\"id\":\"+id+\"," +
                "\"code\":\""+code+"}";
    }
}
