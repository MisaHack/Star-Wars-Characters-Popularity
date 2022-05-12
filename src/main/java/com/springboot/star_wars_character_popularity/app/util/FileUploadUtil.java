package com.springboot.star_wars_character_popularity.app.util;

import com.springboot.star_wars_character_popularity.app.exception.FileNotSavedException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil{

    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException, FileNotSavedException {
        Path uploadPath = Paths.get(uploadDir);

        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        try(InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }
        catch(IOException ioe){
            throw new FileNotSavedException("Could not save image file: " + fileName, ioe);
        }
    }
}
