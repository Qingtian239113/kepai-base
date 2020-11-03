package com.kepai.common.pojos.vo;

import com.kepai.common.base.TokenParse;
import com.kepai.common.pojos.dao.AuthManager;

import java.util.HashMap;

public class LoginVo {

    private String token;
    private String environmentType;
    private HashMap<String, Object> user;


    /**
     * 生成登录信息
     *
     * @param authManager
     * @return
     */
    public static LoginVo parse(AuthManager authManager) {
        LoginVo loginVo = new LoginVo();
        loginVo.user = new HashMap<>(16);
        loginVo.user.put("account", authManager.getAccount());
        loginVo.user.put("nickName", authManager.getNickName());
        loginVo.environmentType = "manager";

        TokenParse tokenParse = new TokenParse(authManager.getId().toString(), loginVo.environmentType);
        loginVo.token = tokenParse.toToken();

        return loginVo;
    }


    ///////////////////////////////////////////////////////////////////////////
    // setter\getter
    ///////////////////////////////////////////////////////////////////////////

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public HashMap<String, Object> getUser() {
        return user;
    }

    public void setUser(HashMap<String, Object> user) {
        this.user = user;
    }

    public String getEnvironmentType() {
        return environmentType;
    }

    public void setEnvironmentType(String environmentType) {
        this.environmentType = environmentType;
    }
}
