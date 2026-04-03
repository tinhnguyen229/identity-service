package com.devteria.identity_service.exportDocument.renderer;

import com.devteria.identity_service.exportDocument.dto.DocumentContext;

import java.util.Map;

public interface DocumentRenderer {
    byte[] render(String templatePath, Map<String, Object> data);
}
