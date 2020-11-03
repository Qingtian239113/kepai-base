package com.kepai.common.service;

import cn.hutool.core.util.StrUtil;
import com.kepai.common.config.exception.ApiException;
import com.kepai.common.utils.TimedCacheHelper;
import org.springframework.stereotype.Service;

/**
 * @author hao
 * @ProjectName cmcc
 * @Copyright Hangzhou ShuoChuang Technology Co.,Ltd All Right Reserved
 * @Description 这里是对文件的描述
 * @data 2019-06-19
 * @note 这里写文件的详细功能和改动
 * @note
 */
@Service
public class TimedCacheService {

    ///////////////////////////////////////////////////////////////////////////
    // 图片验证码缓存
    ///////////////////////////////////////////////////////////////////////////


    /**
     * 缓存 图片验证码
     *
     * @param session
     * @param value
     */
    public void setImageCode(String session, String value) {
        TimedCacheHelper.getTimedCache().put(session, value);
    }


    /**
     * 验证 图片验证码
     *
     * @param session
     */
    public void verifyImage(String session, String value) {

        String cache = TimedCacheHelper.getTimedCache().get(session);
        if (StrUtil.isEmpty(cache) || !StrUtil.equals(cache, value)) {
            throw new ApiException("验证码不正确");
        }
        TimedCacheHelper.getTimedCache().remove(session);
    }


}
