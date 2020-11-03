package com.kepai.common.utils;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.kepai.common.config.exception.ApiException;

public class PasswordUtils {

    private static final int PREFIX_SIZE = 8;


    /**
     * 获取加密密码
     *
     * @param password
     * @return
     */
    public static String buildPassword(String password) {
        String prefix = RandomUtil.randomString(PREFIX_SIZE);
        return prefix + SecureUtil.sha1(password);
    }


    /**
     * 验证密码
     *
     * @param userInput
     * @param encrypted
     * @return
     */
    public static void verifyPassword(String userInput, String encrypted) {
        String userEncrypted = buildPassword(userInput);

        userEncrypted = StrUtil.subSuf(userEncrypted, PREFIX_SIZE);
        encrypted = StrUtil.subSuf(encrypted, PREFIX_SIZE);

        if (!StrUtil.equals(userEncrypted, encrypted)) {
            throw new ApiException("密码不正确，请检查");
        }
    }


}
