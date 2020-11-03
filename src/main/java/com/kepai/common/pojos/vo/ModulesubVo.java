package com.kepai.common.pojos.vo;

import com.kepai.common.pojos.dao.AuthModulesub;

public class ModulesubVo extends AuthModulesub {

    private String moduleName;

    ///////////////////////////////////////////////////////////////////////////
    // setter\getter
    ///////////////////////////////////////////////////////////////////////////

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
