package com.kepai.common.pojos.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName(value = "auth_module")
public class AuthModule {
    /**
     * 模块id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 添加时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(value = "is_deleted")
    private Integer isDeleted;

    /**
     * 模块名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 图片路径
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 排序序号
     */
    @TableField(value = "seq")
    private Integer seq;

    /**
     * 是否公开:0->不公开;1->公开
     */
    @TableField(value = "is_public")
    private Integer isPublic;

    /**
     * 是否在移动端上显示:0->不显示;1->显示;
     */
    @TableField(value = "is_mobile")
    private Integer isMobile;

    /**
     * 移动端访问链接
     */
    @TableField(value = "mobile_url")
    private String mobileUrl;

    /**
     * 获取模块id
     *
     * @return id - 模块id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置模块id
     *
     * @param id 模块id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取添加时间
     *
     * @return create_time - 添加时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置添加时间
     *
     * @param createTime 添加时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return is_deleted
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * @param isDeleted
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取模块名称
     *
     * @return name - 模块名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置模块名称
     *
     * @param name 模块名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取图片路径
     *
     * @return avatar - 图片路径
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置图片路径
     *
     * @param avatar 图片路径
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取排序序号
     *
     * @return seq - 排序序号
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * 设置排序序号
     *
     * @param seq 排序序号
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * 获取是否公开:0->不公开;1->公开
     *
     * @return is_public - 是否公开:0->不公开;1->公开
     */
    public Integer getIsPublic() {
        return isPublic;
    }

    /**
     * 设置是否公开:0->不公开;1->公开
     *
     * @param isPublic 是否公开:0->不公开;1->公开
     */
    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    /**
     * 获取是否在移动端上显示:0->不显示;1->显示;
     *
     * @return is_mobile - 是否在移动端上显示:0->不显示;1->显示;
     */
    public Integer getIsMobile() {
        return isMobile;
    }

    /**
     * 设置是否在移动端上显示:0->不显示;1->显示;
     *
     * @param isMobile 是否在移动端上显示:0->不显示;1->显示;
     */
    public void setIsMobile(Integer isMobile) {
        this.isMobile = isMobile;
    }

    /**
     * 获取移动端访问链接
     *
     * @return mobile_url - 移动端访问链接
     */
    public String getMobileUrl() {
        return mobileUrl;
    }

    /**
     * 设置移动端访问链接
     *
     * @param mobileUrl 移动端访问链接
     */
    public void setMobileUrl(String mobileUrl) {
        this.mobileUrl = mobileUrl;
    }
}
