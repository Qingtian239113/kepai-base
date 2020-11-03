package com.kepai.common.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kepai.common.mapper.BaseSystemLogLoginMapper;
import com.kepai.common.mapper.BaseSystemLogOperationMapper;
import com.kepai.common.pojos.dao.BaseSystemLogLogin;
import com.kepai.common.pojos.dao.BaseSystemLogOperation;
import com.kepai.common.utils.qqwry.IpAddressUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * @author hao
 */
@Service
public class SystemLogService {

    @Autowired
    BaseSystemLogLoginMapper baseSystemLogLoginMapper;
    @Autowired
    BaseSystemLogOperationMapper baseSystemLogOperationMapper;


    ///////////////////////////////////////////////////////////////////////////
    // 登录日志
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 添加登录日志
     *
     * @param managerId
     * @param request
     */
    public void addLoginLog(Integer managerId, HttpServletRequest request) {
        BaseSystemLogLogin logLogin = new BaseSystemLogLogin();
        logLogin.setManagerId(managerId);
        String ip = ServletUtil.getClientIP(request);
        logLogin.setIp(ip);
        IpAddressUtil.IPLocation ipLocation = IpAddressUtil.getIpAddress().getIpLocation(ip);
        logLogin.setAddress(ipLocation.getCountry() + " " + ipLocation.getArea());

        logLogin.setCreateTime(DateUtil.date());
        logLogin.setUpdateTime(DateUtil.date());
        baseSystemLogLoginMapper.insert(logLogin);
    }


    /**
     * 获取登录日志列表
     *
     * @param page
     * @param keyword
     * @return
     */
    public List<Map<String, Object>> listLoginLog(Page<Map<String, Object>> page, String keyword) {
        return baseSystemLogLoginMapper.listLoginLog(page, keyword);
    }


    ///////////////////////////////////////////////////////////////////////////
    // 操作日志
    ///////////////////////////////////////////////////////////////////////////


    /**
     * 添加操作日志
     */
    public void addOperationLog(Integer managerId, String title, String subject, Object params) {
        BaseSystemLogOperation operation = new BaseSystemLogOperation();
        operation.setManagerId(managerId);
        operation.setTitle(title);
        operation.setSubject(subject);
        if (params != null) {
            operation.setParams(JSONUtil.toJsonStr(params));
        }
        operation.setCreateTime(DateUtil.date());
        operation.setUpdateTime(DateUtil.date());
        baseSystemLogOperationMapper.insert(operation);
    }


    /**
     * 获取操作日志列表
     *
     * @param page
     * @param keyword
     * @return
     */
    public List<Map<String, Object>> listOperationLog(Page<Map<String, Object>> page, String keyword) {
        return baseSystemLogLoginMapper.listOperationLog(page, keyword);
    }


}
