package com.kepai.common.pojos.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class RoleModulesubDTO implements Serializable {

    @NotEmpty(message = "请选择角色id")
    public String roleId;
    @NotNull(message = "需要二级模块ids")
    public List<String> modulesubIds;

    ///////////////////////////////////////////////////////////////////////////
    // setter\getter
    ///////////////////////////////////////////////////////////////////////////


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<String> getModulesubIds() {
        return modulesubIds;
    }

    public void setModulesubIds(List<String> modulesubIds) {
        this.modulesubIds = modulesubIds;
    }
}
