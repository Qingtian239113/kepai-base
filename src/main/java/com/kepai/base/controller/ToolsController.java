package com.kepai.base.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.kepai.base.pojos.ApiResp;
import com.kepai.base.config.exception.ApiException;
import com.kepai.base.service.TimedCacheService;
import com.kepai.base.service.ToolsFileService;
import com.kepai.base.utils.FileHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author huang
 * @ProjectName PoliceSpring
 * @Copyright Hangzhou ShuoChuang Technology Co.,Ltd All Right Reserved
 * @Description 这里是对文件的描述
 * @data 2020-04-14
 * @note 这里写文件的详细功能和改动
 * @note
 */
@RestController
@RequestMapping(value = {"/tools"})
public class ToolsController {

    @Autowired
    ToolsFileService toolsFileService;
    @Autowired
    TimedCacheService timedCacheService;

    ///////////////////////////////////////////////////////////////////////////
    // 百度编辑器
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 编辑器配置
     *
     * @return
     */
    @RequestMapping(value = "/ueditor", params = "action=config")
    public Object configJs() {
        JSONObject configObj = new JSONObject();
        // 图片上传
        configObj.putOpt("imageActionName", "uploadFile");
        configObj.putOpt("imageFieldName", "file");
        configObj.putOpt("imageMaxSize", 2048000);
        configObj.putOpt("imageAllowFiles", new String[]{".png", ".jpg", ".jpeg", ".gif"});
        configObj.putOpt("imageCompressEnable", true);
        configObj.putOpt("imageInsertAlign", "none");
        configObj.putOpt("imageUrlPrefix", "");

        // 视频上传
        configObj.putOpt("videoActionName", "uploadFile");
        configObj.putOpt("videoFieldName", "file");
        configObj.putOpt("videoMaxSize", 102400000);
        configObj.putOpt("videoUrlPrefix", "");
        configObj.putOpt("videoAllowFiles", new String[]{".flv", ".mkv", ".avi", ".rm", ".rmvb", ".mp4", ".wmv"});

        // 文件上传
        configObj.putOpt("fileActionName", "uploadFile");
        configObj.putOpt("fileFieldName", "file");
        configObj.putOpt("fileMaxSize", 51200000);
        configObj.putOpt("fileUrlPrefix", "");
        configObj.putOpt("fileAllowFiles", new String[]{".zip", ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx"});

        return configObj;

    }


    /**
     * 编辑器上传文件（小于10M）
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/ueditor", params = "action=uploadFile")
    public Object uploadImg(@RequestParam("file") MultipartFile file) {
        try {
            String fileType = FileTypeUtil.getType(file.getInputStream());
            if (StrUtil.isEmpty(fileType)) {
                fileType = "unknow";
            }
            String filePath = FileHelper.saveMultipartFile(file);
            JSONObject body = new JSONObject();
            body.putOpt("state", "SUCCESS");
            body.putOpt("original", filePath);
            body.putOpt("size", file.getSize());
            body.putOpt("type", fileType);
            body.putOpt("url", filePath);

            return body;
        } catch (Exception e) {
            throw new ApiException("文件流读取失败:" + e.getMessage());
        }
    }


    ///////////////////////////////////////////////////////////////////////////
    // 图形验证码
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 图片验证码
     *
     * @return
     */
    @GetMapping("/code/image_code")
    public void picVerify(HttpSession session, HttpServletResponse response) {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(220, 62, 4, 200);
        timedCacheService.setImageCode(session.getId(), lineCaptcha.getCode());
        response.setContentType("image/png");
        ApiResp.respBytes(response, lineCaptcha.getImageBytes());
    }


    ///////////////////////////////////////////////////////////////////////////
    // 文件上传
    ///////////////////////////////////////////////////////////////////////////


    /**
     * 上传整个文件
     *
     * @param file
     * @return
     */
    @RequestMapping("/file/whole_upload")
    public ApiResp uploadFileLocal(@RequestParam("file") MultipartFile file) {
        String filePath = FileHelper.saveMultipartFile(file);
        return ApiResp.respOK(filePath);
    }


    /**
     * 上传超大文件，建议超过20M的使用这个方法
     *
     * @return
     */
    @RequestMapping(value = "/file/chunk_start")
    public ApiResp fileRegister(String fileMd5, String fileExt) {
        toolsFileService.register(fileMd5, fileExt);
        return ApiResp.respOK("");
    }

    /**
     * 校验文件块
     *
     * @return
     */
    @RequestMapping("/file/chunk_check")
    public ApiResp checkChunk(String fileMd5, Integer chunk) {
        boolean exist = toolsFileService.checkChunk(fileMd5, chunk);
        return ApiResp.respOK(exist);
    }

    /**
     * 上传文件块
     *
     * @return
     */
    @RequestMapping("/file/chunk_upload")
    public ApiResp checkChunk(@RequestParam("file") MultipartFile file, String fileMd5, Integer chunk) {
        toolsFileService.uploadChunk(file, fileMd5, chunk);
        return ApiResp.respOK("");
    }


    /**
     * 合并文件
     *
     * @param fileMd5
     * @param fileExt
     * @return
     */
    @RequestMapping("/file/chunk_merge")
    public ApiResp fileMerge(String fileMd5, String fileExt) {
        String path = toolsFileService.mergeChunks(fileMd5, fileExt);
        return ApiResp.respOK(path);
    }


}
