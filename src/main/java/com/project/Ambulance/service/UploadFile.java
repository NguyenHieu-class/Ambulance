package com.project.Ambulance.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFile {
	
	String uploadSingleFile( MultipartFile file);
	
	String uploadMultiFile(MultipartFile[] file);
	void removeFile(String nameFile);
	
	String uploadFileDocument( MultipartFile file);
}
