package com.example.letsquiz.controllers;

import com.example.letsquiz.entity.file.FileData;
import com.example.letsquiz.service.FileDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
@CrossOrigin
public class FileController {

    private final FileDataService fileDataService;

    @PostMapping("/uploadAvatar/{username}")
    public void uploadAvatar(@RequestParam("file") MultipartFile file, @PathVariable String username){
        fileDataService.uploadAvatar(file, username);
    }

    @GetMapping("/download/{fileName}")
    public byte[] downloadFile(@PathVariable String fileName){
        return fileDataService.downloadFile(fileName);
    }

    @PostMapping("/uploadTestImage")
    public FileData uploadTestImage(@RequestParam("file") MultipartFile file){
        return fileDataService.uploadTestImage(file);
    }

}
