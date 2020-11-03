package com.kepai.common.pojos.dto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author hao
 */
public class IdDTO implements Serializable {

    @NotEmpty(message = "需要id")
    private String id;

    ///////////////////////////////////////////////////////////////////////////
    // setter\getter
    ///////////////////////////////////////////////////////////////////////////

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
