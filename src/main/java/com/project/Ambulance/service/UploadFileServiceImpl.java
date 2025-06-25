package com.project.Ambulance.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Service
public class UploadFileServiceImpl implements UploadFileService {

    private static final int LENGTH_MAX = 7;
    private static final String UPLOAD_DIR = "uploads/";

    @Override
    public String uploadSingleFile(MultipartFile file) {
        String fileName = "";
        Path path = Paths.get(UPLOAD_DIR);
        try {
            InputStream inputStream = file.getInputStream();
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

            fileName = timestamp + randomString() + ".jpg";
            Files.copy(inputStream, path.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    @Override
    public String uploadMultiFile(MultipartFile[] files) {
        StringBuilder fileNames = new StringBuilder();
        for (MultipartFile file : files) {
            fileNames.append(uploadSingleFile(file)).append(";");
        }
        if (fileNames.length() > 0) {
            fileNames.deleteCharAt(fileNames.length() - 1); // Xoá dấu ; cuối cùng
        }
        return fileNames.toString();
    }

    @Override
    public void removeFile(String nameFile) {
        Path path = Paths.get(UPLOAD_DIR);
        try {
            Files.deleteIfExists(path.resolve(nameFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String uploadFileDocument(MultipartFile file) {
        String fileName = file.getOriginalFilename().trim();
        Path path = Paths.get(UPLOAD_DIR);
        try {
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream, path.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    // Sinh chuỗi random
    private String randomString() {
        String alphanumeric = "0123456789abcdefghijklmnopqrstuv";
        StringBuilder randomStr = new StringBuilder(LENGTH_MAX);
        Random random = new Random();
        for (int i = 0; i < LENGTH_MAX; i++) {
            int index = random.nextInt(alphanumeric.length());
            randomStr.append(alphanumeric.charAt(index));
        }
        return randomStr.toString();
    }
}
