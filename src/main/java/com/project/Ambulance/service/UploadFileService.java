package com.project.Ambulance.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {

    String uploadSingleFile(MultipartFile file);

    String uploadMultiFile(MultipartFile[] files);

    void removeFile(String nameFile);

    String uploadFileDocument(MultipartFile file);
}
