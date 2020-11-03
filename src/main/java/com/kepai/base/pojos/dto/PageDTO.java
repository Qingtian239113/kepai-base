package com.kepai.base.pojos.dto;

import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

/**
 * @author hao
 */
public class PageDTO {

    private String start;
    private String length;


    /**
     * 启用分页
     *
     * @return
     */
    public <T> Page<T> pageObj() {
        Page<T> page = new Page<>();
        page.setSize(defaultSize());
        page.setCurrent(defaultCurrent());
        return page;
    }


    /**
     * 启用分页
     *
     * @return
     */
    public Page<Map<String, Object>> pageMap() {
        Page<Map<String, Object>> page = new Page<>();
        page.setSize(defaultSize());
        page.setCurrent(defaultCurrent());
        return page;
    }

    public long defaultCurrent() {
        return NumberUtil.parseLong(start) / defaultSize() + 1;
    }

    public long defaultSize() {
        long size = NumberUtil.parseLong(length);
        if (size == 0) {
            size = 10;
        }
        return size;
    }


    ///////////////////////////////////////////////////////////////////////////
    // setter\getter
    ///////////////////////////////////////////////////////////////////////////

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
}
