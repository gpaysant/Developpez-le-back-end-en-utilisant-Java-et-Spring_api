package com.openclassrooms.api.services;


import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceImpl implements FileService {

    public static final String DIRECTORY_UPLOAD = "/uploads/";

    @Autowired
    private ServletContext servletContext;

    @Override
    public String uploadFile(HttpServletRequest httpServletRequest, MultipartFile file) throws IOException {
        String destDirectory = servletContext.getRealPath(DIRECTORY_UPLOAD);
        Path pathDirectory = Path.of(destDirectory);

        if (Files.notExists(pathDirectory)) {
            Files.createDirectories(pathDirectory);
        }

        String fileName = file.getOriginalFilename();
        Path destFilePath = pathDirectory.resolve(fileName);
        if (Files.exists(destFilePath)) {
            Files.delete(destFilePath);
        }
        Files.copy(file.getInputStream(), destFilePath);

        // Build url from context of servlet
        String imageUrl = httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName() +
                ":" + httpServletRequest.getServerPort() + httpServletRequest.getContextPath() +
                DIRECTORY_UPLOAD + fileName;

        return imageUrl;
    }
}
