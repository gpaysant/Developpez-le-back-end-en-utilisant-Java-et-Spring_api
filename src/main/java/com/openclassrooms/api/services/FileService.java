package com.openclassrooms.api.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String uploadFile(HttpServletRequest httpServletRequest, MultipartFile file) throws IOException;
}
