package com.kepai.common.pojos.dto;

import javax.validation.constraints.NotEmpty;

public class UpdateManagerDTO {

    @NotEmpty(message = "请输入昵称")
    private String nickName;
    private String password;
    private String phone;

    ///////////////////////////////////////////////////////////////////////////
    // setter\getter
    ///////////////////////////////////////////////////////////////////////////

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
