package com.ayd2.intelafbackend.controllers.datafile;

import com.ayd2.intelafbackend.dto.files.DataFileResponseDTO;
import com.ayd2.intelafbackend.exceptions.UploadDataFileException;
import com.ayd2.intelafbackend.services.DataFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/v1/datafile")
public class DataFileController {
    DataFileService dataFileService;

    @Autowired
    public DataFileController(DataFileService dataFileService) {
        this.dataFileService = dataFileService;
    }

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<DataFileResponseDTO> uploadDataFile(@RequestParam("file")MultipartFile file) throws UploadDataFileException {
        return ResponseEntity.ok(dataFileService.handleDataFile(file));
    }

}
