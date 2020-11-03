package com.kepai.base.pojos.dto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author hao
 */
public class ModuleDTO implements Serializable {

    @NotEmpty(message = "请输入模块ID")
    private String id;
    @NotEmpty(message = "请输入模块名称")
    private String name;
    private String avatar;
    private Integer isPublic;
    private Integer isMobile;
    private String mobileUrl;


    ///////////////////////////////////////////////////////////////////////////
    // setter\getter
    ///////////////////////////////////////////////////////////////////////////


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    public Integer getIsMobile() {
        return isMobile;
    }

    public void setIsMobile(Integer isMobile) {
        this.isMobile = isMobile;
    }

    public String getMobileUrl() {
        return mobileUrl;
    }

    public void setMobileUrl(String mobileUrl) {
        this.mobileUrl = mobileUrl;
    }
}
