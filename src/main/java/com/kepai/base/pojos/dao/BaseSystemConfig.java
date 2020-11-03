package com.kepai.base.pojos.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName(value = "base_system_config")
public class BaseSystemConfig {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(value = "is_deleted")
    private Integer isDeleted;

    @TableField(value = "config_key")
    private String configKey;

    @TableField(value = "config_val")
    private String configVal;

    /**
     * 类型:0-文本，1-富文本，2-图片
     */
    @TableField(value = "config_type")
    private Integer configType;

    @TableField(value = "remark")
    private String remark;

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
     * @return config_key
     */
    public String getConfigKey() {
        return configKey;
    }

    /**
     * @param configKey
     */
    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    /**
     * @return config_val
     */
    public String getConfigVal() {
        return configVal;
    }

    /**
     * @param configVal
     */
    public void setConfigVal(String configVal) {
        this.configVal = configVal;
    }

    /**
     * 获取类型:0-文本，1-富文本，2-图片
     *
     * @return config_type - 类型:0-文本，1-富文本，2-图片
     */
    public Integer getConfigType() {
        return configType;
    }

    /**
     * 设置类型:0-文本，1-富文本，2-图片
     *
     * @param configType 类型:0-文本，1-富文本，2-图片
     */
    public void setConfigType(Integer configType) {
        this.configType = configType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
