package com.devteria.identity_service.exportDocument.template.user;

import com.devteria.identity_service.exportDocument.constants.ExportFormat;
import com.devteria.identity_service.exportDocument.constants.Model;
import com.devteria.identity_service.exportDocument.dto.DocumentContext;
import com.devteria.identity_service.exportDocument.renderer.DocumentRenderer;
import com.devteria.identity_service.exportDocument.renderer.DocxRenderer;
import com.devteria.identity_service.exportDocument.renderer.PdfRenderer;
import com.devteria.identity_service.exportDocument.template.TemplateStrategy;
import com.devteria.identity_service.user.entity.User;
import com.devteria.identity_service.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


@Component
@AllArgsConstructor
public class UserProfileStrategy implements TemplateStrategy {
    private final UserRepository userRepository;

    private final DocxRenderer docxRenderer;
    private final PdfRenderer pdfRenderer;

    @Override
    public String getTemplatePath() {
        return "templates/USER_PROFILE.docx";
    }

    @Override
    public String getKey() {
        return Model.USER.name() + "_" + "PROFILE";
    }

    private static String getOutputFileName() {
        return Model.USER.name() + "_" + "PROFILE"
                + "_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    @Override
    public void populateData(DocumentContext context) {
        User user = userRepository.findById(context.getResId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Map<String, Object> data = new HashMap<>();
        data.put("firstName", user.getFirstName());
        data.put("lastName", user.getLastName());


        context.setData(data);
        context.setOutputFileName(getOutputFileName());
    }

    @Override
    public byte[] render(DocumentContext context) {
        byte[] content = null;
        DocumentRenderer renderer;
        switch (context.getFormat()) {
            case ExportFormat.DOCX:
                renderer = docxRenderer;
                break;
            case ExportFormat.PDF:
                renderer = pdfRenderer;
                break;
            default:
                throw new RuntimeException("Format not supported");
        }
        content = renderer.render(getTemplatePath(), context.getData());
        return content;
    }
}
