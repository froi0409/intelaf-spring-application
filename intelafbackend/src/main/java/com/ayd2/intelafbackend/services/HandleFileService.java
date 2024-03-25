package com.ayd2.intelafbackend.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface HandleFileService {

    public void handleDataFile(MultipartFile file);


}
