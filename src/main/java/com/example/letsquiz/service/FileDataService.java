package com.example.letsquiz.service;

import com.example.letsquiz.entity.file.FileData;
import com.example.letsquiz.entity.user.AppUser;
import com.example.letsquiz.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@RequiredArgsConstructor
public class FileDataService {

    private final FileRepository fileRepository;
    public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads/";
    private final AppUserService appUserService;

    public void uploadAvatar(MultipartFile file, String username){
        String filename = username + "_avatar.jpg";
        Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
        try {
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileData avatar = FileData.builder()
                .name(filename)
                .build();
        fileRepository.save(avatar);
        AppUser user = appUserService.findUserByUsername(username);
        user.setAvatar(avatar);
        appUserService.save(user);
    }
    public FileData uploadTestImage(MultipartFile file){
        String filename = UUID.randomUUID() + ".jpg";
        Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
        try {
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileData testImage = FileData.builder()
                .name(filename)
                .build();
        fileRepository.save(testImage);
        return testImage;
    }
    public Optional<FileData> getByName(String filename){
        return this.fileRepository.findFileDataByName(filename);
        }
    public byte[] downloadFile(String fileName){
        try {
            Path fileStorage = get(DIRECTORY, fileName).toAbsolutePath().normalize();
            return Files.readAllBytes(new File(fileStorage.toString()).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
