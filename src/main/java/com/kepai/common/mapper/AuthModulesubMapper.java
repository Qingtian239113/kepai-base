package com.kepai.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kepai.common.pojos.dao.AuthModulesub;
import com.kepai.common.pojos.vo.ModulesubVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AuthModulesubMapper extends BaseMapper<AuthModulesub> {

    /**
     * 调整排序
     *
     * @param id
     * @param seq
     */
    @Update("update auth_modulesub set seq = seq+1 where seq>=#{seq}; "
            + "update auth_modulesub set seq=#{seq} where id=#{id};")
    void changeSeqById(@Param("id") String id, @Param("seq") Integer seq);


    /**
     * 获取seq最大值
     *
     * @return
     */
    @Select("select COALESCE(max(seq),0) from auth_modulesub")
    int getMaxSeq();


    /**
     * 获取二级列表
     *
     * @return
     */
    List<ModulesubVo> getModulesubList();


}
