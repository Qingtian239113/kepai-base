package com.kepai.base.pojos.dto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class UserLoginDTO implements Serializable {

    @NotEmpty(message = "请输入账号")
    private String account;
    @NotEmpty(message = "请输入密码")
    private String password;
    @NotEmpty(message = "请输入验证码")
    private String code;


    ///////////////////////////////////////////////////////////////////////////
    // setter\getter
    ///////////////////////////////////////////////////////////////////////////

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
