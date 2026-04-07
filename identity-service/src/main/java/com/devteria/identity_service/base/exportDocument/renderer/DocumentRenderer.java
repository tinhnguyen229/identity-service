package com.devteria.identity_service.base.exportDocument.renderer;

import java.util.Map;

public interface DocumentRenderer {
    byte[] render(String templatePath, Map<String, Object> data);
}
