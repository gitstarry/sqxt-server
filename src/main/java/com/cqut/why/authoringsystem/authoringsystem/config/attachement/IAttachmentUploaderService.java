package com.cqut.why.authoringsystem.authoringsystem.config.attachement;

import com.cqut.why.authoringsystem.authoringsystem.config.util.GlobalExceptionHandler.BusinessException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IAttachmentUploaderService {
    /**
     * 上传Base64编码文件
     * @param base64 base64编码
     * @return 本模块的实例
     */
    UploaderAttachment uploadBase64File(String fileName, String base64, String contentType);

    /**
     * 上传web
     * @param file web上传的表单附件
     * @return 本模块的实例
     */
    UploaderAttachment uploadMultipartFile(MultipartFile file);

    /* 上传web
     * @param files web上传的表单附件
     * @return 本模块的实例
     */
    List<UploaderAttachment> uploadMultipartFile(List<MultipartFile> files);

    /**
     * 上传file
     * @param prefix 前缀
     * @param files 本地附件集合
     * @return 本模块的实例
     */
    List<UploaderAttachment> uploadFiles(String prefix, List<File> files);

    UploaderAttachment uploadMultipartFile(String prefix, MultipartFile file);

    List<UploaderAttachment> uploadMultipartFile(String prefix, List<MultipartFile> files);

    /**
     * 按输入流上传
     * @param key 文件名
     * @param stream 流实例
     */
    UploaderAttachment uploadStream(String prefix, String key, InputStream stream, String contentType) throws Exception;

    /**
     * 上传到本地文件
     * @param file web上传的表单附件
     * @return 本模块的实例
     */
    UploaderAttachment uploadMultipartFileLocal(MultipartFile file);

    String shareAttachment(String objName, Integer limited) throws BusinessException;

    InputStream getObject(String objName) throws BusinessException;

    String getObjectUrlByName(String objName, Integer expires, String downloadName) throws BusinessException;

    String getObjectUrl(String objName, Integer expires, Map<String, String> reqParams) throws BusinessException;

    OutputStream getObjectOutPutStream(OutputStream output, String objName) throws BusinessException;

    void copyObjects(HashMap<String, String> objNames) throws BusinessException;

    void copyObject(String objName, String destObjName) throws BusinessException;

    void removeObject(String objName) throws BusinessException;

    void removeObjects(List<String> objNames) throws BusinessException;
}
