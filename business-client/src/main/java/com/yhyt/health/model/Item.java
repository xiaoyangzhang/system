package com.yhyt.health.model;

import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Item {
    private Long id;

    private String code;

    private String brand;

    private String name;

    private Long categoryDictId;

    private BigDecimal price;

    private Integer ratio;

    private Integer expireDate;

    private Byte releaseClient;

    private Byte releasePlace;

    private Byte topOrder;

    private String description;

    private String navigationPicUrl;

    private String topPicUrl;

    private Byte isQuote;

    private String quoteUrl;

    private String titlePic;

    private String subtitle;

    private String h5Url;

    private Byte state;

    private Date onshelfDay;

    private Date offshelfDay;

    /**
     * 商品数量（预留字段，暂时不用，不用存值）
     */
    private Integer count;

    private String createOperator;

    private Date updateTime;

    private Date createTime;

    private String body;

    private Long version;

    /**
     * 内容详情图片列表
     */
    private List<String> contentDetailsPicture;

    private Long hospitalId;

    private Long departmentId;

    /**
     * 商品关联疾病列表
     */
    private List<Long> dictDiseaseIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getCategoryDictId() {
        return categoryDictId;
    }

    public void setCategoryDictId(Long categoryDictId) {
        this.categoryDictId = categoryDictId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getRatio() {
        return ratio;
    }

    public void setRatio(Integer ratio) {
        this.ratio = ratio;
    }

    public Integer getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Integer expireDate) {
        this.expireDate = expireDate;
    }

    public Byte getReleaseClient() {
        return releaseClient;
    }

    public void setReleaseClient(Byte releaseClient) {
        this.releaseClient = releaseClient;
    }

    public Byte getReleasePlace() {
        return releasePlace;
    }

    public void setReleasePlace(Byte releasePlace) {
        this.releasePlace = releasePlace;
    }

    public Byte getTopOrder() {
        return topOrder;
    }

    public void setTopOrder(Byte topOrder) {
        this.topOrder = topOrder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getNavigationPicUrl() {
        return navigationPicUrl;
    }

    public void setNavigationPicUrl(String navigationPicUrl) {
        this.navigationPicUrl = navigationPicUrl == null ? null : navigationPicUrl.trim();
    }

    public String getTopPicUrl() {
        return topPicUrl;
    }

    public void setTopPicUrl(String topPicUrl) {
        this.topPicUrl = topPicUrl == null ? null : topPicUrl.trim();
    }

    public Byte getIsQuote() {
        return isQuote;
    }

    public void setIsQuote(Byte isQuote) {
        this.isQuote = isQuote;
    }

    public String getQuoteUrl() {
        return quoteUrl;
    }

    public void setQuoteUrl(String quoteUrl) {
        this.quoteUrl = quoteUrl == null ? null : quoteUrl.trim();
    }

    public String getTitlePic() {
        return titlePic;
    }

    public void setTitlePic(String titlePic) {
        this.titlePic = titlePic == null ? null : titlePic.trim();
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle == null ? null : subtitle.trim();
    }

    public String getH5Url() {
        return h5Url;
    }

    public void setH5Url(String h5Url) {
        this.h5Url = h5Url == null ? null : h5Url.trim();
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Date getOnshelfDay() {
        return onshelfDay;
    }

    public void setOnshelfDay(Date onshelfDay) {
        this.onshelfDay = onshelfDay;
    }

    public Date getOffshelfDay() {
        return offshelfDay;
    }

    public void setOffshelfDay(Date offshelfDay) {
        this.offshelfDay = offshelfDay;
    }

    public String getCreateOperator() {
        return createOperator;
    }

    public void setCreateOperator(String createOperator) {
        this.createOperator = createOperator == null ? null : createOperator.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

    /**
     * 商品数量
     * @return
     */
    public Integer getCount() {  return count;  }

    /**
     * 商品数量
     * @param count
     */
    public void setCount(Integer count) {  this.count = count; }

    /**
     * 乐观锁 版本号
     * @return
     */
    public Long getVersion() { return version; }

    /**
     * 乐观锁 版本号
     * @param version
     */
    public void setVersion(Long version) {  this.version = version; }

    public List<String> getContentDetailsPicture() {
        return contentDetailsPicture;
    }

    public void setContentDetailsPicture(List<String> contentDetailsPicture) {
        this.contentDetailsPicture = contentDetailsPicture;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public List<Long> getDictDiseaseIds() {
        return dictDiseaseIds;
    }

    public void setDictDiseaseIds(List<Long> dictDiseaseIds) {
        this.dictDiseaseIds = dictDiseaseIds;
    }

    /**
     * 判断商品必填字端是否为空
     * @return
     */
    public boolean isEmptyItem(){

        if(StringUtils.isEmpty(this.getBrand())){return false;}
        if(StringUtils.isEmpty(this.getName())){return false;}
        if(StringUtils.isEmpty(this.getDescription())){return false;}
        if(StringUtils.isEmpty(this.getNavigationPicUrl())){return false;}
        if(StringUtils.isEmpty(this.getTitlePic())){return  false;}
        if(null == this.getCount()){return false;}
        if(null == this.getCategoryDictId()){return  false;}
        if(null == this.getPrice()){return  false;}
        if(null == this.getRatio()){return  false;}
        if(null == this.getExpireDate()){return  false;}
        if(null == this.getReleaseClient()){return  false;}
        if(null == this.getReleasePlace()){return  false;}
        if(null == this.getState()){return  false;}
        if(null == this.getDictDiseaseIds() && this.getDictDiseaseIds().size()<1){return false;}
        if(null == this.getContentDetailsPicture() && this.getContentDetailsPicture().size()<1){return false;}
        return true;
    }

    public boolean isShelvesItemEmpty(){
        if(null == this.getId() || null == this.getState()){
            return  false;
        }
        return  true;
    }
}