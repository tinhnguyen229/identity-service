package com.devteria.identity_service.exportDocument.renderer;


import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Map;

@Component
public class DocxRenderer implements DocumentRenderer {

    public File generateWordFromTemplate(String templatePath, Map<String, Object> data) {
        try {
            InputStream templateFile = getClass().getClassLoader().getResourceAsStream(templatePath);
            if (templateFile == null){
                throw new RuntimeException("template file not found");
            }

            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(templateFile, TemplateEngineKind.Freemarker);

            IContext context = report.createContext();

            data.forEach((k, v) -> context.put(k, v));

            File wordFile = File.createTempFile("xdoc_", ".docx");
            try (OutputStream out = new FileOutputStream(wordFile)) {
                report.process(context, out);
            }

            return wordFile;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public byte[] render(String templatePath, Map<String, Object> data) {
        try {
            File wordFile = generateWordFromTemplate(templatePath, data);
            byte[] fileBytes = Files.readAllBytes(wordFile.toPath());
            return fileBytes;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
