package com.kepai.base.pojos;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kepai.base.config.exception.ApiException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class TokenParse {

    private String id;
    private String environmentType;


    public TokenParse(String id, String environmentType) {
        this.id = id;
        this.environmentType = environmentType;
    }

    /**
     * 获取登陆信息
     *
     * @param token
     * @return
     */
    public static TokenParse parse(String token) {
        try {
            DecodedJWT decode = JWT.decode(token);
            String id = decode.getAudience().get(0);
            String environmentType = decode.getAudience().get(1);
            return new TokenParse(id, environmentType);
        } catch (Exception e) {
            throw new ApiException(ApiResp.respNeedLogin("请重新登录"));
        }
    }

    /**
     * 获取登录信息
     *
     * @return
     */
    public static TokenParse parse() {
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return parse(request.getHeader("token"));
    }


    /**
     * 设置token
     */
    public String toToken() {
        return JWT.create().withAudience(id, environmentType)
                .withExpiresAt(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000))
                .sign(Algorithm.HMAC256("kepai"));
    }

    ///////////////////////////////////////////////////////////////////////////
    // setter、getter
    ///////////////////////////////////////////////////////////////////////////

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnvironmentType() {
        return environmentType;
    }

    public void setEnvironmentType(String environmentType) {
        this.environmentType = environmentType;
    }
}
