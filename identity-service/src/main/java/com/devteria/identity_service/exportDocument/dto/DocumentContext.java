package com.devteria.identity_service.exportDocument.dto;

import com.devteria.identity_service.exportDocument.constants.ExportFormat;
import com.devteria.identity_service.exportDocument.constants.Model;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Map;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DocumentContext {
    private String resId;
    private Model resModel;
    private String templateType;
    private ExportFormat format;
    private Map<String, Object> data;   // dữ liệu thực tế từ DB
    private String outputFileName;
}
