package com.example.libapp.service;

import com.example.libapp.configs.ApplicationContextHolder;
import com.example.libapp.dao.UploadsDAO;
import com.example.libapp.domain.Uploads;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.lang.module.Configuration;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 11:26 AM 7/14/22 on Thursday in July
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUploadsService {
    private static FileUploadsService instance;
    private final UploadsDAO uploadsDAO = ApplicationContextHolder.getBean(UploadsDAO.class);

    public Uploads upload(Part partFile) {
        try {
            String contentType = partFile.getContentType();
            long size = partFile.getSize();
            String filename = partFile.getSubmittedFileName();
            String[] split = filename.split("\\.");
            String filenameExtension = split[split.length - 1];
            String generatedName = System.currentTimeMillis() + "." + filenameExtension;
            String rootPath = "/home/zuhriddin/IdeaProjects/javaee/uploads/";
            String path = generatedName;
            Uploads uploads = Uploads
                    .builder()
                    .contentType(contentType)
                    .originalName(filename)
                    .size(size)
                    .generatedName(generatedName)
                    .path(path)
                    .build();
            uploadsDAO.save(uploads);
            Path uploadPath = Path.of(rootPath + generatedName);
            Files.copy(partFile.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);
            return uploads;
        } catch (IOException e) {
            throw new RuntimeException("Something wrong try again");
        }
    }

    public String getOriginalName(String generatedName){
        return uploadsDAO.findByGeneratedName(generatedName);
    }

    public static FileUploadsService getInstance() {
        if (instance == null) {
            instance = new FileUploadsService();
        }
        return instance;
    }
}
