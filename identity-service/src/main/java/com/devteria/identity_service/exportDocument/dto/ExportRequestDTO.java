package com.devteria.identity_service.exportDocument.dto;


import com.devteria.identity_service.exportDocument.constants.ExportFormat;
import com.devteria.identity_service.exportDocument.constants.Model;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExportRequestDTO {
    Model resModel;
    String resId;
    ExportFormat exportFormat;
    String templateType;
}
