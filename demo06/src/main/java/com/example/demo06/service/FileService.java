package com.example.demo06.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo06.model.FileBoard;
import com.example.demo06.repository.FileRepository;

@Service
public class FileService {
	
	@Autowired
	private FileRepository fileRepository;
	
	public void fildInsert(FileBoard fboard, String uploadFolder) {
		UUID uuid = UUID.randomUUID();
		MultipartFile f = fboard.getUpload();
		String uploadFileName = ""; //파일선택안됨
		if(!f.isEmpty()){//파일선택됨
			uploadFileName=uuid.toString()+"_"+f.getOriginalFilename();
			File saveFile = new File(uploadFolder, uploadFileName); // 파일객체
			
			try {
				f.transferTo(saveFile); // 파일 업로드됨.
				fboard.setFileimage(uploadFileName);//파일이름세팅.
				
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		// 저장 전 업로드와 파일이름 가져와야 저장됨.
		fileRepository.save(fboard);
	}
	
	public List<FileBoard> fileList(){
		return fileRepository.findAll();
	}
	
	public Long count() {
		return fileRepository.count();
	}
	
	///////////////////////////////////////////////////////////////////
	
	public void fildInsert(FileBoard fboard) {
		UUID uuid = UUID.randomUUID();
		MultipartFile f = fboard.getUpload();
		String uploadFileName = "";
		if(!f.isEmpty()){
			uploadFileName=uuid.toString()+"_"+f.getOriginalFilename();
			File saveFile = new File(uploadFileName);
			
			try {
				f.transferTo(saveFile);
				fboard.setFileimage(uploadFileName);
				
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		fileRepository.save(fboard);
	}
	
}
