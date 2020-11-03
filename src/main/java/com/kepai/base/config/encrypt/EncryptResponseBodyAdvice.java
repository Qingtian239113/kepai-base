package com.kepai.base.config.encrypt;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.LogFactory;
import com.kepai.base.config.encrypt.annotation.Encrypt;
import com.kepai.base.pojos.ApiResp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * @author hao
 */
@ControllerAdvice
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Value("${common.encrypt.private-key}")
    public String privateKey;
    @Value("${common.encrypt.public-key}")
    public String publicKey;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends
            HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        boolean encrypt = false;
        if (AnnotationUtil.getAnnotation(returnType.getMethod(), Encrypt.class) != null) {
            encrypt = true;
        }

        if (!encrypt) {
            return body;
        }

        try {
            String content = JSONUtil.toJsonStr(body);
            ApiResp resp = JSONUtil.toBean(content, ApiResp.class);
            if (resp.getCode() == ApiResp.CODE_OK && resp.getData() != null) {
                // 使用私钥进行加密
                RSA rsa = SecureUtil.rsa(privateKey, publicKey);
                byte[] enc = rsa.encrypt(resp.getData().toString(), KeyType.PrivateKey);
                resp.setData(Base64.encode(enc));
            }
            return resp;
        } catch (Exception var17) {
            LogFactory.get().info("加密数据异常", var17);
        }

        return null;
    }

}
