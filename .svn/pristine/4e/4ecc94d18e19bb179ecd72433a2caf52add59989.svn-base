package com.yhyt.health.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class DictCity {
    private Long id;

    private Byte level;

    private Long parentId;

    private String name;

    private String abbrName;

    private String fullSpell;

    private String abbrSpell;

    private String cityCode;

    private Integer sortNo;

    private String regionLevel;

    private Byte isMainCity;

    private String mappingCode;

    private Date createTime;
    
    /**
     * 是否有叶子节点
     */
    private int havChild;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAbbrName() {
        return abbrName;
    }

    public void setAbbrName(String abbrName) {
        this.abbrName = abbrName == null ? null : abbrName.trim();
    }

    public String getFullSpell() {
        return fullSpell;
    }

    public void setFullSpell(String fullSpell) {
        this.fullSpell = fullSpell == null ? null : fullSpell.trim();
    }

    public String getAbbrSpell() {
        return abbrSpell;
    }

    public void setAbbrSpell(String abbrSpell) {
        this.abbrSpell = abbrSpell == null ? null : abbrSpell.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getRegionLevel() {
        return regionLevel;
    }

    public void setRegionLevel(String regionLevel) {
        this.regionLevel = regionLevel == null ? null : regionLevel.trim();
    }

    public Byte getIsMainCity() {
        return isMainCity;
    }

    public void setIsMainCity(Byte isMainCity) {
        this.isMainCity = isMainCity;
    }

    public String getMappingCode() {
        return mappingCode;
    }

    public void setMappingCode(String mappingCode) {
        this.mappingCode = mappingCode == null ? null : mappingCode.trim();
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public int getHavChild() {
		return havChild;
	}

	public void setHavChild(int havChild) {
		this.havChild = havChild;
	}
    
}