package com.kepai.common.pojos.dto;

public class IdStateDTO extends PageDTO {

    private String id;
    private String state;

    ///////////////////////////////////////////////////////////////////////////
    // setter、getter
    ///////////////////////////////////////////////////////////////////////////

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
