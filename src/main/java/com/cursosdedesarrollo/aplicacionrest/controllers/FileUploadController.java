package com.cursosdedesarrollo.aplicacionrest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/*
LLamada Curl
curl -F "file=@/path/to/your/file.txt" http://localhost:8080/api/v1/upload

 */

@RestController
@RequestMapping("/api/v1")
public class FileUploadController {

    private static String UPLOAD_DIR = "/tmp/uploads";

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        createDirectoryIfNotExists(UPLOAD_DIR);
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please select a file!", HttpStatus.OK);
        }

        try {
            // Save the file to the specified directory
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_DIR+ "/" + file.getOriginalFilename());
            Files.write(path, bytes);

            return new ResponseEntity<>("File uploaded successfully: " + path.toString(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload the file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public static void createDirectoryIfNotExists(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created: " + directoryPath);
            } else {
                System.out.println("Failed to create directory: " + directoryPath);
            }
        } else {
            System.out.println("Directory already exists: " + directoryPath);
        }
    }
}
