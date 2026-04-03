package com.devteria.identity_service.exportDocument.dto;

import com.devteria.identity_service.exportDocument.constants.ExportFormat;
import com.devteria.identity_service.exportDocument.constants.Model;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ExportResponseDTO {
    String resId;
    Model resModel;
    String fileName;
    ExportFormat exportFormat;
    byte[] content;
}
