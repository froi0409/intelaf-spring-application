package com.ayd2.intelafbackend.services;

import com.ayd2.intelafbackend.dto.files.DataFileResponseDTO;
import com.ayd2.intelafbackend.exceptions.UploadDataFileException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DataFileService {

    DataFileResponseDTO handleDataFile(MultipartFile file) throws UploadDataFileException;

    List<String> verifySystemData();

}
