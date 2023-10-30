package com.cloud.ms.component.qiniucloud;

import com.cloud.ms.component.qiniucloud.config.QiNiuClient;
import com.cloud.ms.component.qiniucloud.config.QiNiuCloudConfig;
import com.cloud.ms.component.qiniucloud.enums.FileTypeEnum;
import com.cloud.ms.component.qiniucloud.utils.UploadFileUtils;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

/**
 * @author ms
 */
@Service
public class QiNiuUploadService {

    @Resource
    private QiNiuCloudConfig qiNiuCloudConfig;


    /**
     * 上传文件
     *
     * @param file 文件
     * @param type 文件类型
     * @return 返回文件的url
     */
    public String uploadQiNiu(File file, FileTypeEnum type) {
        UploadManager uploadManager = QiNiuClient.getInstance().getUploadManager();
        String bucket = type.getBucket();
        String token = buildToken(bucket);
        try {
            Response response = uploadManager.put(file, null, token);
            DefaultPutRet defaultPutRet = response.jsonToObject(DefaultPutRet.class);
            return defaultPutRet.key;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    private String buildToken(String bucket) {
        Auth auth = Auth.create(qiNiuCloudConfig.getAccessKey(), qiNiuCloudConfig.getSecretKey());
        return auth.uploadToken(bucket);
    }

    /**
     * 上传文件
     *
     * @param file         文件
     * @param fileTypeEnum 文件类型
     * @return 返回文件的url
     */
    public String uploadQiNiu(MultipartFile file, FileTypeEnum fileTypeEnum) {
        UploadManager uploadManager = QiNiuClient.getInstance().getUploadManager();
        String bucket = fileTypeEnum.getBucket();
        String token = buildToken(bucket);
        try {
            // file hash key 计算
            String hash = UploadFileUtils.getSha256(file);
            String key = fileTypeEnum.getPath() + hash;
            Response response = uploadManager.put(file.getBytes(), key, token);
            DefaultPutRet defaultPutRet = response.jsonToObject(DefaultPutRet.class);
            return qiNiuCloudConfig.getDomain() + defaultPutRet.key;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }
}
