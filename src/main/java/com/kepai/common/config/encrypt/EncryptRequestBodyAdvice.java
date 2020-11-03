package com.kepai.common.config.encrypt;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Objects;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.LogFactory;
import com.kepai.common.config.encrypt.annotation.Decrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

/**
 * @author hao
 */
@ControllerAdvice
public class EncryptRequestBodyAdvice implements RequestBodyAdvice {


    @Value("${common.encrypt.private-key}")
    public String privateKey;
    @Value("${common.encrypt.public-key}")
    public String publicKey;


    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                           Class<? extends HttpMessageConverter<?>> converterType) {

        boolean decrypt = false;
        if (AnnotationUtil.getAnnotation(parameter.getMethod(), Decrypt.class) != null) {
            decrypt = true;
        }

        if (decrypt) {
            try {
                return new EncryptRequestBodyAdvice.DecryptHttpInputMessage(inputMessage, "UTF-8");
            } catch (Exception var7) {
                LogFactory.get().info("数据解密失败", var7);
            }
        }

        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
                                Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
                                  Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }


    class DecryptHttpInputMessage implements HttpInputMessage {

        private HttpHeaders headers;
        private InputStream body;

        DecryptHttpInputMessage(HttpInputMessage inputMessage, String charset) throws Exception {
            this.headers = inputMessage.getHeaders();
            String bodyStr = IoUtil.read(inputMessage.getBody(), charset);
            JSONObject map = JSONUtil.parseObj(bodyStr);
            String content = map.getStr("content");
            if (!StrUtil.isEmpty(content)) {
                RSA rsa = SecureUtil.rsa(privateKey, publicKey);
                byte[] enc = rsa.encrypt(content, KeyType.PrivateKey);
                this.body = IoUtil.toStream(enc);
            }
        }

        @Override
        public InputStream getBody() {
            return this.body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return this.headers;
        }
    }

}
