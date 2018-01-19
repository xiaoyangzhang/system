package com.yhyt.health.model.dto;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 商品的查询字端封装参数对象
 */
@Component
public class ItemQueryDTO implements Serializable {

    /**
     * 商品编号
     */
    private String code;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 所属医院名称
     */
    private String hospitalName;

    /**
     * 所属科室名称
     */
    private String departmentName;

    /**
     * 商品状态
     */
    private Byte state;

    /**
     * 商品分类
     */
    private Long categoryId;

    /**
     * 发布时间begin
     */
    private String beginTime;

    /**
     * 发布时间end
     */
    private String endTime;

    /**
     * pageNo
     */
    private Integer pageIndex;

    /**
     * pageSize
     */
    private Integer pageSize;

    /**
     *
     * @return 商品编号
     */
    public String getCode() {
        return code;
    }


    /**
     *
     * @return 商品名称
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return 商品分类
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     *
     * @return 发布时间begin
     */
    public String getBeginTime() {
        return beginTime;
    }

    /**
     *
     * @return 发布时间end
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     *
     * @return pageNo
     */
    public Integer getPageIndex() {
        return pageIndex;
    }

    /**
     *
     * @return pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     *
     * @return 所属医院名称
     */
    public String getHospitalName() {
        return hospitalName;
    }

    /**
     *
     * @return 所属部门名称
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * 商品编码
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 商品名称
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 商品分类id
     * @param categoryId
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     *  发布时间begin
     * @param beginTime
     */
    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * 发布时间end
     * @param endTime
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     *  pageno
     * @param pageIndex
     */
    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    /**
     * pageSize
     * @param pageSize
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 医院名称
     * @param hospitalName
     */
    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    /**
     * 科室名称
     * @param departmentName
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Byte getState() { return state; }

    public void setState(Byte state) { this.state = state; }
}
