package com.kepai.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kepai.common.pojos.dao.AuthManager;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AuthManagerMapper extends BaseMapper<AuthManager> {


    /**
     * 获取管理员列表
     *
     * @param page
     * @return
     */
    List<Map<String, Object>> getUserList(IPage page);


    /**
     * 通过userId获取一级模块
     *
     * @param roleId
     * @return
     */
    List<Map<String, Object>> getModuleOneByRoleGroup(@Param("roleId") Integer roleId);


    /**
     * 通过userId获取二级模块
     *
     * @param roleId
     * @return
     */
    List<Map<String, Object>> getModuleTwoByRoleGroup(@Param("roleId") Integer roleId,
                                                      @Param("moduleId") String moduleId);

}
