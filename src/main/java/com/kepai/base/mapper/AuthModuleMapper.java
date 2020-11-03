package com.kepai.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kepai.base.pojos.dao.AuthModule;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AuthModuleMapper extends BaseMapper<AuthModule> {


    /**
     * 调整排序
     *
     * @param id
     * @param seq
     */
    @Update("update auth_module set seq = seq+1 where seq>=#{seq}; "
            + "update auth_module set seq=#{seq} where id=#{id};")
    void changeSeqById(@Param("id") String id, @Param("seq") Integer seq);


    /**
     * 获取seq最大值
     *
     * @return
     */
    @Select("select COALESCE(max(seq),0) from auth_module")
    int getMaxSeq();

}
