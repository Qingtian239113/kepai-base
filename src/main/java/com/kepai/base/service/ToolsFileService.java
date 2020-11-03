package com.kepai.base.service;

import cn.hutool.core.io.FileUtil;
import com.kepai.base.config.exception.ApiException;
import com.kepai.base.utils.FileHelper;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ToolsFileService {


    /**
     * 根据文件md5得到文件路径
     * 规则：
     * 文件名：md5+文件扩展名
     *
     * @param fileMd5 文件md5值
     * @param fileExt 文件扩展名
     * @return 文件路径
     */
    private String getFileAbsolutePath(String fileMd5, String fileExt) {
        String file = FileHelper.staticAbs() + getUploaderPath(fileMd5, fileExt);
        FileUtil.mkParentDirs(file);
        return file;
    }

    /**
     * 获取upload目录下的路径
     *
     * @param fileMd5
     * @param fileExt
     * @return
     */
    private String getUploaderPath(String fileMd5, String fileExt) {
        return "/upload/" + fileExt + "/" + fileMd5 + "." + fileExt;
    }

    /**
     * 得到块文件的根目录，上传完成之后便于删除
     *
     * @param fileMd5
     * @return
     */
    private String getChunkFolder(String fileMd5) {
        String folder = FileHelper.staticAbs() + "/upload/" + fileMd5 + "/";
        FileUtil.mkdir(folder);
        return folder;
    }

    /**
     * 检查待上传文件是否存在
     *
     * @param fileMd5
     * @param fileExt
     */
    public void register(String fileMd5, String fileExt) {
        //   检查文件是否上传
        //   1.   获取文件的路径
        String filePath = getFileAbsolutePath(fileMd5, fileExt);
        File file = new File(filePath);

        //   文件存在直接返回
        if (file.exists()) {
        }
    }

    /**
     * 检查块文件
     *
     * @param fileMd5
     * @param chunk
     * @return
     */
    public boolean checkChunk(String fileMd5, Integer chunk) {
        //得到块文件所在路径
        String chunkFileFolderPath = getChunkFolder(fileMd5);
        //块文件的文件名称以1,2,3..序号命名，没有扩展名
        File chunkFile = new File(chunkFileFolderPath + chunk);
        return chunkFile.exists();
    }

    /**
     * 块文件上传
     *
     * @param file
     * @param fileMd5
     * @param chunk
     */
    public void uploadChunk(MultipartFile file, String fileMd5, Integer chunk) {
        if (file == null) {
            throw new ApiException("请选择文件后上传");
        }
        //块文件
        File chunkfile = new File(getChunkFolder(fileMd5) + chunk);
        //上传的块文件
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = file.getInputStream();
            outputStream = new FileOutputStream(chunkfile);
            IOUtils.copy(inputStream, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiException("块文件上传失败");
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 合并块文件
     *
     * @param fileMd5
     * @param fileExt
     */
    public String mergeChunks(String fileMd5, String fileExt) {
        //获取块文件的路径
        String chunkFileFolderPath = getChunkFolder(fileMd5);
        File chunkFileFolder = new File(chunkFileFolderPath);

        //合并文件路径
        File mergeFile = new File(getFileAbsolutePath(fileMd5, fileExt));
        //创建合并文件,合并文件存在先删除再创建
        if (mergeFile.exists()) {
            mergeFile.delete();
        }
        boolean newFile = false;
        try {
            newFile = mergeFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!newFile) {
            throw new ApiException("文件创建失败");
        }
        //获取块文件，此列表是已经排好序的列表
        List<File> chunkFiles = getChunkFiles(chunkFileFolder);
        //合并文件
        mergeFile = mergeFile(mergeFile, chunkFiles);
        if (mergeFile == null) {
            throw new ApiException("文件合并失败");
        }

        FileUtil.del(chunkFileFolder);

        return getUploaderPath(fileMd5, fileExt);

    }

    /**
     * 获取所有块文件
     *
     * @param chunkFileFolder
     * @return
     */
    private List<File> getChunkFiles(File chunkFileFolder) {
        //获取路径下的所有块文件
        File[] chunkFiles = chunkFileFolder.listFiles();
        if (chunkFiles == null) {
            return new ArrayList<>();
        }
        //将文件数组转成list，并排序
        List<File> chunkFileList = new ArrayList<>(Arrays.asList(chunkFiles));
        //排序
        chunkFileList.sort((o1, o2) -> {
            if (Integer.parseInt(o1.getName()) > Integer.parseInt(o2.getName())) {
                return 1;
            }
            return -1;
        });
        return chunkFileList;
    }

    /**
     * 合并文件
     *
     * @param mergeFile
     * @param chunkFiles
     * @return
     */
    private File mergeFile(File mergeFile, List<File> chunkFiles) {
        try {
            //创建写文件对象
            RandomAccessFile raf_write = new RandomAccessFile(mergeFile, "rw");
            //遍历分块文件开始合并
            //读取文件缓冲区
            byte[] b = new byte[1024];
            for (File chunkFile : chunkFiles) {
                RandomAccessFile raf_read = new RandomAccessFile(chunkFile, "r");
                int len = -1;
                //读取分块文件
                while ((len = raf_read.read(b)) != -1) {
                    //向合并文件中写数据
                    raf_write.write(b, 0, len);
                }
                raf_read.close();
            }
            raf_write.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return mergeFile;
    }

}
