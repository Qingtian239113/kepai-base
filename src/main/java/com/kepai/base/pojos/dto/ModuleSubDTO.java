package com.kepai.base.pojos.dto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author hao
 */
public class ModuleSubDTO implements Serializable {

    @NotEmpty(message = "请输入模块ID")
    private String id;
    @NotEmpty(message = "请输入模块名称")
    private String name;
    private String avatar;
    @NotEmpty(message = "请输入一级模块id")
    private String moduleId;
    @NotEmpty(message = "模块类型：1前端，2-后端，3都有")
    private String type;

    ///////////////////////////////////////////////////////////////////////////
    // setter\getter
    ///////////////////////////////////////////////////////////////////////////


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
