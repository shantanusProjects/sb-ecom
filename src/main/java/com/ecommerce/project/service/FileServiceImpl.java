package com.ecommerce.project.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        //file names of current / original file
        String originalFileName = file.getOriginalFilename();
        //Generating a unique file Name
        String randomId = UUID.randomUUID().toString();
        String fileName = randomId.concat(originalFileName.substring(originalFileName.lastIndexOf('.')));
        //mat.png --> 1234 ---> 1234.png
        String filePath = path + File.separator+fileName;
        //Check if path exist and create
        File folder = new File(path);
        if(!folder.exists()){
            folder.mkdirs();
        }
        //Upload to Server
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName;
    }
}
