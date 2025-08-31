package com.example.S3UploaderApplication.controller;

import com.example.S3UploaderApplication.service.S3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@RestController
@RequestMapping("/api/s3")
public class FileUploadController {

    private final S3Service s3Service;
    Logger logger= LoggerFactory.getLogger("FileUploadController");


    public FileUploadController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload() {
        try {

            // Hardcoded file path
            File file = new File("C:/Users/karnakar/Downloads/Karnakar_Konda.pdf");

            // Call S3 upload method
            String fileUrl = s3Service.uploadFile(file);
            return ResponseEntity.ok("File uploaded: " + fileUrl);
        } catch (IOException e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Upload failed: " + e.getMessage());
        }
    }
}
