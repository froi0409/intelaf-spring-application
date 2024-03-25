package com.ayd2.intelafbackend.dto.files;

import com.ayd2.intelafbackend.entities.ErrorEntity;
import lombok.Value;

import java.util.List;

@Value
public class DataFileResponseDTO {
    int records;
    List<ErrorEntity> errors;
}
