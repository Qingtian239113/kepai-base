package com.kepai.common.utils;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;

public class TimedCacheHelper {

    private static volatile TimedCache<String, String> timedCache;

    /**
     * 获取一个缓存
     *
     */
    public static TimedCache<String, String> getTimedCache() {
        if (timedCache == null) {
            synchronized (TimedCacheHelper.class) {
                if (timedCache == null) {
                    timedCache = CacheUtil.newTimedCache(60 * 1000);
                    timedCache.schedulePrune(1000);
                }
            }
        }
        return timedCache;
    }

}
