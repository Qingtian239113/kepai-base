package com.kepai.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kepai.common.pojos.dao.AuthRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AuthRoleMapper extends BaseMapper<AuthRole> {
    /**
     * 获取角色绑定的二级模块
     *
     * @param roleId
     * @return
     */
    List<Map<String, Object>> getRoleModulesubList(@Param("roleId") String roleId);

    /**
     * 绑定二级模块
     *
     * @param roleId
     * @return
     */
    void modulesubAdd(@Param("roleId") String roleId, @Param("list") List<String> list);


}
