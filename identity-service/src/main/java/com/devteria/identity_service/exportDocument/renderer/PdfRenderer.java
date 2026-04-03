package com.devteria.identity_service.exportDocument.renderer;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Map;

@Component
public class PdfRenderer extends DocxRenderer {
    public File convertDocxToPdf(File wordFile) throws Exception {
        File pdfFile = File.createTempFile("xdoc_", ".pdf");
        try (InputStream docxInputStream = new FileInputStream(wordFile);
             OutputStream pdfOutputStream = new FileOutputStream(pdfFile)) {

            XWPFDocument document = new XWPFDocument(docxInputStream);
            PdfOptions options = PdfOptions.create();
            PdfConverter.getInstance().convert(document, pdfOutputStream, options);
        }
        return pdfFile;
    }

    public byte[] render(String templatePath, Map<String, Object> data) {
        try {
            File wordFile = generateWordFromTemplate(templatePath, data);
            File pdfFile = convertDocxToPdf(wordFile);
        
            byte[] fileBytes = Files.readAllBytes(pdfFile.toPath());

            safeDelete(wordFile);
            safeDelete(pdfFile);
            
            return fileBytes;
            // return Base64.getEncoder().encodeToString(fileBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void safeDelete(File file) {
        if (file != null && file.exists()) {
            file.delete();
        }
    }
}
