package com.yhyt.health.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class DictDisease extends Persistable{


    private String code;

    private String name;

    private String tags;

    private String fullSpell;

    private String abbrSpell;

    private Date createTime;

    private List<DictDepartment> departmentList;
    private String depts;

    public String getDepts() {
        return depts;
    }

    public void setDepts(String depts) {
        this.depts = depts;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public List<DictDepartment> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<DictDepartment> departmentList) {
		this.departmentList = departmentList;
	}
    
}