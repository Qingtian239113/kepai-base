package com.kepai.base.utils;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;


/**
 * @author huang
 * @ProjectName ApiBuilderSpring
 * @Copyright Hangzhou ShuoChuang Technology Co.,Ltd All Right Reserved
 * @Description 这里是对文件的描述
 * @data 2020-02-25
 * @note 这里写文件的详细功能和改动
 * @note
 */
public class DefaultUtil {


    /**
     * 转换为null
     *
     * @param obj
     * @return
     */
    public static String toNull(String obj) {
        if (StrUtil.isEmpty(obj)) {
            return null;
        }
        return obj;
    }


    /**
     * 默认参数
     *
     * @param value
     * @param def
     * @return
     */
    public static <T> T initValue(T value, T def) {
        T result = value;
        if (ObjectUtil.isEmpty(result)) {
            result = def;
        }
        return result;
    }


    /**
     * 后6为字符
     *
     * @param val
     * @return
     */
    public static String last6Char(String val) {
        return StrUtil.subSufByLength(val, 6);
    }


}
