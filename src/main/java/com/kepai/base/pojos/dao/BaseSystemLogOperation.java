package com.kepai.base.pojos.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName(value = "base_system_log_operation")
public class BaseSystemLogOperation {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(value = "is_deleted")
    private Integer isDeleted;

    /**
     * 管理员id
     */
    @TableField(value = "manager_id")
    private Integer managerId;

    /**
     * 日志标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 小标题
     */
    @TableField(value = "subject")
    private String subject;

    /**
     * 参数
     */
    @TableField(value = "params")
    private String params;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
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
     * 获取管理员id
     *
     * @return manager_id - 管理员id
     */
    public Integer getManagerId() {
        return managerId;
    }

    /**
     * 设置管理员id
     *
     * @param managerId 管理员id
     */
    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    /**
     * 获取日志标题
     *
     * @return title - 日志标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置日志标题
     *
     * @param title 日志标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取小标题
     *
     * @return subject - 小标题
     */
    public String getSubject() {
        return subject;
    }

    /**
     * 设置小标题
     *
     * @param subject 小标题
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * 获取参数
     *
     * @return params - 参数
     */
    public String getParams() {
        return params;
    }

    /**
     * 设置参数
     *
     * @param params 参数
     */
    public void setParams(String params) {
        this.params = params;
    }
}
