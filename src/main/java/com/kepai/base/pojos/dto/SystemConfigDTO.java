package com.kepai.base.pojos.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SystemConfigDTO {

    private Integer id;

    @NotEmpty(message = "请输入key")
    private String configKey;

    @NotEmpty(message = "请输入值")
    private String configVal;

    @NotNull(message = "请选择值类型")
    private Integer configType;

    private String remark;

    ///////////////////////////////////////////////////////////////////////////
    // setter\getter
    ///////////////////////////////////////////////////////////////////////////

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigVal() {
        return configVal;
    }

    public void setConfigVal(String configVal) {
        this.configVal = configVal;
    }

    public Integer getConfigType() {
        return configType;
    }

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
