package com.kepai.base.pojos.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author hao
 */
public class SortDTO implements Serializable {

    @NotEmpty(message = "需要改变排序的id")
    private String id;
    @NotNull(message = "请输入新的排序值")
    private Integer seq;


    ///////////////////////////////////////////////////////////////////////////
    // setter\getter
    ///////////////////////////////////////////////////////////////////////////

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}
