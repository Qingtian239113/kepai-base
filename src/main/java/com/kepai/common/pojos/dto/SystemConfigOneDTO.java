package com.kepai.common.pojos.dto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author hao
 */
public class SystemConfigOneDTO implements Serializable {

    @NotEmpty(message = "请提供key参数")
    private String key;

    ///////////////////////////////////////////////////////////////////////////
    // setter\getter
    ///////////////////////////////////////////////////////////////////////////

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
