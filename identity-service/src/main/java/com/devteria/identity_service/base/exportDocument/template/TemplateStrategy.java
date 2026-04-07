package com.devteria.identity_service.base.exportDocument.template;

import com.devteria.identity_service.base.exportDocument.dto.DocumentContext;

public interface TemplateStrategy {
    /** Key định danh template: MODEL_TEMPLATETYPE, vd: USER_PROFILE */
    String getKey();

    String getTemplatePath();

    /**
     * Populate data vào context.
     * Template tự fetch data tương ứng với model của nó.
     */
    void populateData(DocumentContext context);

    /**
     * Render ra byte[] (DOCX hoặc PDF tùy format trong context)
     */
    byte[] render(DocumentContext context);
}
