package com.kepai.common.pojos.dto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author hao
 */
public class RoleDTO implements Serializable {

    private Integer id;
    @NotEmpty(message = "请输入角色名称")
    private String name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
