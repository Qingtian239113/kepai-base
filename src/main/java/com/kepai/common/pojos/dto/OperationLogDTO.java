package com.kepai.common.pojos.dto;

import java.io.Serializable;

public class OperationLogDTO implements Serializable {

    private Integer managerId;
    private String title;
    private String subject;
    private String params;

    ///////////////////////////////////////////////////////////////////////////
    // setter\getter
    ///////////////////////////////////////////////////////////////////////////

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
