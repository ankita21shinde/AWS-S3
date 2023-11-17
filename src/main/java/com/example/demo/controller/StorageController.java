package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.StorageService;

@RestController
public class StorageController {
	
	@Autowired
	private StorageService service;
	
	
	//to upload file
	@PostMapping(path = "/upload")
	public ResponseEntity<String> uploadFile(@RequestParam(value="file")MultipartFile file) {
		return new ResponseEntity<>(service.uploadFile(file),HttpStatus.OK);
	}
	
	
	
	//to download file
	@GetMapping("/download/{fileName}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName){
		byte[] data=service.downloadFile(fileName);
		ByteArrayResource resource=new ByteArrayResource(data);
		
		return ResponseEntity
				.ok()
				.contentLength(data.length)
				.header("Content-type","application/octect-stream")
				.header("content-disposition","attachement:filename\""+fileName+"\"")
				.body(resource);
	}
	
	
	
	//to delete file
	@DeleteMapping("/delete/{fileName}")
	public ResponseEntity<String> deleteFile(@PathVariable String fileName){
		return new ResponseEntity<>(service.deleteFile(fileName),HttpStatus.OK);
	}
}
