package com.kepai.base.utils;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.LogFactory;
import com.kepai.base.config.exception.ApiException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class FileHelper {

    /**
     * 获取static的绝对路径
     *
     * @return
     */
    public static String staticAbs() {
        ClassPathResource classPathResource = new ClassPathResource("static");
        File rootFile = new File(classPathResource.getPath());
        return rootFile.getAbsolutePath();
    }


    /**
     * 保存文件
     *
     * @param file
     */
    public static String saveMultipartFile(MultipartFile file) {
        try {
            String fileType = FileTypeUtil.getType(file.getInputStream());
            if (StrUtil.isEmpty(fileType)) {
                fileType = "unknow";
            }

            String filePath = String.format("/upload/%s/%s.%s", fileType, IdUtil.simpleUUID(), fileType);
            String outputPath = String.format("%s/%s", staticAbs(), filePath);

            InputStream stream = file.getInputStream();
            FileUtil.writeFromStream(stream, outputPath);
            IoUtil.close(stream);

            return filePath;
        } catch (Exception e) {
            LogFactory.get().error(e);
            throw new ApiException("文件流读取失败:" + e.getMessage());
        }
    }


    /**
     * 保存文件
     *
     * @param file
     * @return
     */
    public static String saveFile(File file) {
        try {
            String fileType = FileTypeUtil.getType(file);
            if (StrUtil.isEmpty(fileType)) {
                fileType = "unknow";
            }

            String filePath = String.format("/upload/%s/%s.%s", fileType, IdUtil.simpleUUID(), fileType);
            String outputPath = String.format("%s/%s", staticAbs(), filePath);

            InputStream stream = new FileInputStream(file);
            FileUtil.writeFromStream(stream, outputPath);
            IoUtil.close(stream);

            return filePath;
        } catch (Exception e) {
            LogFactory.get().error(e);
            throw new ApiException("文件流读取失败:" + e.getMessage());
        }
    }


}
