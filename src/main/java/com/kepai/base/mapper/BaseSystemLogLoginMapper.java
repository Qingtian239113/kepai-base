package com.kepai.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kepai.base.pojos.dao.BaseSystemLogLogin;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BaseSystemLogLoginMapper extends BaseMapper<BaseSystemLogLogin> {

    /**
     * 分页获取登录日志
     *
     * @param page
     * @param keyword
     * @return
     */
    List<Map<String, Object>> listLoginLog(Page page, @Param("keyword") String keyword);

    /**
     * 分页获取操作日志
     *
     * @param page
     * @param keyword
     * @return
     */
    List<Map<String, Object>> listOperationLog(Page page, @Param("keyword") String keyword);

}
