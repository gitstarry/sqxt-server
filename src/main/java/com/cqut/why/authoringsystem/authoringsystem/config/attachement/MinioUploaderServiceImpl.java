package com.cqut.why.authoringsystem.authoringsystem.config.attachement;

import com.cqut.why.authoringsystem.authoringsystem.config.util.GlobalExceptionHandler.BusinessException;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.Result;
import io.minio.errors.MinioException;
import io.minio.messages.DeleteError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;

@Component
public class MinioUploaderServiceImpl implements IAttachmentUploaderService {
    private final MinioProperties minioProperties;
    private final MinioClient minioClient;

    @Autowired
    MinioUploaderServiceImpl(MinioProperties minioProperties) throws Exception {
        this.minioProperties = minioProperties;
        this.minioClient = new MinioClient(minioProperties.getEndpoint(), minioProperties.getAccessKey(), minioProperties.getAccessSecret());
        try {
            boolean isExist = minioClient.bucketExists(minioProperties.getBucket());

            if (!isExist) {
                minioClient.makeBucket(minioProperties.getBucket());
            }
        } catch (MinioException e) {
            e.printStackTrace();
        }
    }

    @Value("${attachment.local.basePath:/}")
    private String localBasePath;

    /**
     * 上传到本地文件
     *
     * @param file web上传的表单附件
     * @return 本模块的实例
     */
    @Override
    public UploaderAttachment uploadMultipartFileLocal(MultipartFile file) {
        String now = new Date().toString();
        String fileName = now + "_" + file.getOriginalFilename().toLowerCase();
        String filePath = this.localBasePath + File.separator + fileName;
        String[] fileTypes = fileName.split("\\.");
        String extension = fileTypes[fileTypes.length - 1].toLowerCase();
        File desFile = new File(filePath);
        if (!desFile.getParentFile().exists()) {
            desFile.mkdirs();
        }
        try {
            file.transferTo(desFile);
            return new UploaderAttachment(file.getOriginalFilename(), null, extension, filePath,
                    filePath, desFile.length());
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 上传base64
    @Override
    public UploaderAttachment uploadBase64File(String fileName, String base64, String contentType) {
        byte[] buffer = Base64.getDecoder().decode(base64.substring(base64.indexOf(",") + 1));
        // 生成文件路径
        String filePath = this.localBasePath + File.separator + fileName;
        UploaderAttachment uploaderAttachment = null;

        try {
            // 生成文件
            File imageFile = new File(filePath);
            boolean createFile = imageFile.createNewFile();
            if (!createFile)
                return null;
            FileOutputStream imageStream = new FileOutputStream(imageFile);
            imageStream.write(buffer);
            imageStream.flush();
            imageStream.close();
            // 上传至minio
            uploaderAttachment = this.uploadStream(null, fileName, new FileInputStream(imageFile), contentType);
            // 删除本地创建的文件
            imageFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return uploaderAttachment;
    }

    /**
     * 上传web
     *
     * @param file web上传的表单附件
     * @return 本模块的实例
     */
    @Override
    public UploaderAttachment uploadMultipartFile(MultipartFile file) {
        try {
            return uploadStream(null, file.getOriginalFilename(), file.getInputStream(), file.getContentType());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 上传web
     *
     * @param files web上传的表单附件
     * @return 本模块的实例
     */
    @Override
    public List<UploaderAttachment> uploadMultipartFile(List<MultipartFile> files) {
        List<UploaderAttachment> uploaderAttachments = new ArrayList<>();
        if (files != null) {
            files.forEach(i -> {
                try {
                    uploaderAttachments.add(uploadStream(null, i.getOriginalFilename(), i.getInputStream(), i.getContentType()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        return uploaderAttachments;
    }


    /**
     * 上传单个文件
     *
     * @param prefix 前缀
     * @param file   web上传的表单附件
     * @return 本模块的实例
     */
    @Override
    public UploaderAttachment uploadMultipartFile(String prefix, MultipartFile file) {
        try {
            return uploadStream(prefix, file.getOriginalFilename(), file.getInputStream(), file.getContentType());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 上传文件集合
     *
     * @param prefix 前缀
     * @param files  web上传的表单附件
     * @return 本模块的实例
     */
    @Override
    public List<UploaderAttachment> uploadMultipartFile(String prefix, List<MultipartFile> files) {
        List<UploaderAttachment> uploaderAttachments = new ArrayList<>();
        if (files != null && files.size() > 0)
            files.forEach(i -> {
                try {
                    uploaderAttachments.add(uploadStream(prefix, i.getOriginalFilename(), i.getInputStream(), i.getContentType()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        return uploaderAttachments;
    }

    /**
     * 上传文件File集合
     *
     * @param prefix 前缀
     * @param files  web上传的表单附件
     * @return 本模块的实例
     */
    @Override
    public List<UploaderAttachment> uploadFiles(String prefix, List<File> files) {
        List<UploaderAttachment> uploaderAttachments = new ArrayList<>();
        if (files != null && files.size() > 0)
            files.forEach(i -> {
                try {
                    File file = i;
                    FileInputStream fileInputStream = new FileInputStream(file);
                    String fileName = i.getName();
                    String contentType = i.getName().split("\\.")[1];
                    uploaderAttachments.add(uploadStream(prefix, fileName, fileInputStream, contentType));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        return uploaderAttachments;
    }


    /**
     * 按输入流上传
     *
     * @param prefix           前缀
     * @param originalFileName 文件名
     * @param stream           流实例
     * @param contentType      类型
     */
    @Override
    public UploaderAttachment uploadStream(String prefix, String originalFileName, InputStream stream, String contentType) {
        // 生成文件
        File tempFile = null;
        boolean createFile = false;

        if (prefix == null)
            prefix = minioProperties.getPathPrefix() + "/";
        else
            prefix = prefix + "/";
        try {
            // 文件名（不包含后缀）
            String realName = originalFileName.substring(0, originalFileName.lastIndexOf("."));
            // 文件类型
            String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();
            // 当前时间戳
            String now = Long.toString(new Date().getTime());
            // 新文件名
            String objName = prefix + now + "_" + originalFileName.toLowerCase();
            // 文件长度
            Long fileLength = Integer.toUnsignedLong(stream.available());

            // 转视频接口为mp4
            if (checkIfNeedConvert(extension)) {
                String tempFileName = "temp_" + now + "_" + realName + ".mp4";
                // 生成文件
                tempFile = new File(tempFileName);
                createFile = tempFile.createNewFile();
                if (!createFile)
                    return null;

//                FfmpegConvert.convert(stream, tempFile);

                return this.uploadStream(prefix, realName + ".mp4", new FileInputStream(tempFile), "video/mpeg4");
            }

            PutObjectOptions putObjectOptions = new PutObjectOptions(-1, PutObjectOptions.MAX_PART_SIZE) {
                {
                    setContentType(contentType);
                }
            };

            minioClient.putObject(minioProperties.getBucket(), objName, stream, putObjectOptions);

            return new UploaderAttachment(originalFileName, objName, contentType, extension, minioProperties.getEndpoint() + "/" + minioProperties.getBucket() + "/" + objName,
                    minioProperties.getVisitUrl() + "/" + minioProperties.getBucket() + "/" + objName, fileLength);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (createFile && tempFile.exists())
                // 删除本地创建的文件
                tempFile.delete();
        }
    }

    // 校验是否需要转换格式
    private boolean checkIfNeedConvert(String extension) {
        List<String> extensions = new ArrayList<>();
        extensions.add("mov");

        return extensions.contains(extension);
    }

    // 根据minio文件唯一名称获取该文件输入流
    @Override
    public String shareAttachment(String objName, Integer limited) throws BusinessException {
        try {
            return getObjectUrl(objName, limited, null);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    // 根据minio文件唯一名称获取该文件输入流
    @Override
    public InputStream getObject(String objName) throws BusinessException {
        try {
            return minioClient.getObject(minioProperties.getBucket(), objName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    // 根据minio文件唯一名称以及下载名获取文件限时访问地址，默认60 * 60秒（1小时）
    @Override
    public String getObjectUrlByName(String objName, Integer expires, String downloadName) throws BusinessException {
        try {
            if (expires == null)
                expires = 60 * 60;

            Map<String, String> reqParams = new HashMap<>();
            reqParams.put("response-content-type", "application/force-download;filename=" + URLEncoder.encode(downloadName, "utf-8"));
            reqParams.put("response-content-disposition", "attachment; filename=" + URLEncoder.encode(downloadName, "utf-8"));

            return this.getObjectUrl(objName, expires, reqParams);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    // 根据minio文件唯一名称获取文件限时访问地址，默认60 * 60秒（1小时）
    @Override
    public String getObjectUrl(String objName, Integer expires, Map<String, String> reqParams) throws BusinessException {
        try {
            if (expires == null)
                expires = 60 * 60;
//            this.visitMinioClient = new MinioClient(minioProperties.getVisitUrl(), minioProperties.getAccessKey(), minioProperties.getAccessSecret());
//            String url = visitMinioClient.presignedGetObject(minioProperties.getBucket(), objName, expires, reqParams);
            String url = minioClient.presignedGetObject(minioProperties.getBucket(), objName, expires, reqParams);
            //使用visiturl替换掉endpoint
            System.out.println("minioProperties.getVisitUrl()=" + minioProperties.getVisitUrl());
            System.out.println("minioProperties.getEndpoint()=" + minioProperties.getEndpoint());
            System.out.println("oldUrl=" + url);
            url = url.replace(minioProperties.getEndpoint(), minioProperties.getVisitUrl());
            System.out.println("newUrl=" + url);
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    // 获取文件输出，用于直接返回输出流
    @Override
    public OutputStream getObjectOutPutStream(OutputStream output, String objName) throws BusinessException {
        try {
            if (output == null)
                output = new ByteArrayOutputStream();
            InputStream inputStream = this.getObject(objName);
            byte[] bts = new byte[8192];
            int len = -1;
            while ((len = inputStream.read(bts)) != -1) {
                output.write(bts, 0, len);
            }

            return output;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    // 复制文件
    @Override
    public void copyObjects(HashMap<String, String> objNames) throws BusinessException {
        try {
            for (String objName : objNames.keySet()) {
                minioClient.copyObject(minioProperties.getBucket(), objName, null, null, minioProperties.getBucket(), objNames.get(objName), null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    // 复制文件集合
    @Override
    public void copyObject(String objName, String destObjName) throws BusinessException {
        try {
            minioClient.copyObject(minioProperties.getBucket(), objName, null, null, minioProperties.getBucket(), destObjName, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    // 删除文件
    @Override
    public void removeObject(String objName) throws BusinessException {
        try {
            minioClient.removeObject(minioProperties.getBucket(), objName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    // 删除文件集合
    @Override
    public void removeObjects(List<String> objNames) throws BusinessException {
        try {
            for (Result<DeleteError> errorResult : minioClient.removeObjects(minioProperties.getBucket(), objNames)) {
                DeleteError error = errorResult.get();
                System.out.println("删除失败 '" + error.objectName() + "'. 错误原因:" + error.message());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }
}
