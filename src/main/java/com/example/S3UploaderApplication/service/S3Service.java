package com.example.S3UploaderApplication.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@Service
public class S3Service {

    @Value("${aws.bucketName}")
    private String bucketName;

    private final S3Client s3Client;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(File file) throws IOException {
        String key = System.currentTimeMillis() + "_" + file.getName();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        s3Client.putObject(putObjectRequest, Paths.get(file.getAbsolutePath()));

        // Construct public URL manually
        return "https://" + bucketName + ".s3.amazonaws.com/" + key;
    }
}
