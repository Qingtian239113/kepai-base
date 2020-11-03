package com.kepai.common.pojos.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName(value = "auth_modulesub")
public class AuthModulesub {
    /**
     * id
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

    @TableField(value = "avatar")
    private String avatar;

    /**
     * 排序序号
     */
    @TableField(value = "seq")
    private Integer seq;

    /**
     * 模块id
     */
    @TableField(value = "module_id")
    private String moduleId;

    /**
     * 1->前端模块;2->后台模块;3->两端都有
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 获取id
     *
     * @return id - id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
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
     * @return avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar
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
     * 获取模块id
     *
     * @return module_id - 模块id
     */
    public String getModuleId() {
        return moduleId;
    }

    /**
     * 设置模块id
     *
     * @param moduleId 模块id
     */
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    /**
     * 获取1->前端模块;2->后台模块;3->两端都有
     *
     * @return type - 1->前端模块;2->后台模块;3->两端都有
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1->前端模块;2->后台模块;3->两端都有
     *
     * @param type 1->前端模块;2->后台模块;3->两端都有
     */
    public void setType(Integer type) {
        this.type = type;
    }
}
