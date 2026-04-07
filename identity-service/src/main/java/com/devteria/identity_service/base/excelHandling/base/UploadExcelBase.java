package com.devteria.identity_service.base.excelHandling.base;


import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class UploadExcelBase {
    public static final long DEFAULT_MAX_UPLOAD_SIZE_BYTES = 5L * 1024 * 1024;

    private static final String XLSX_MIME_TYPE =
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public List<List<String>> readFileExcel(MultipartFile file) throws Exception{
        validateFileUpload(file);

        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        List<List<String>> excelData = new ArrayList<>();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            List<String> rowData = new ArrayList<>();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case STRING:
                        rowData.add(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        rowData.add(String.valueOf(cell.getNumericCellValue()));
                        break;
                    case BOOLEAN:
                        rowData.add(String.valueOf(cell.getBooleanCellValue()));
                        break;
                    default:
                        rowData.add("");
                        break;
                }
            }
            excelData.add(rowData);
        }
        return excelData;
    }

    private void validateFileUpload(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        if (file.getSize() > DEFAULT_MAX_UPLOAD_SIZE_BYTES) {
            throw new RuntimeException("File exceeds max upload size bytes");
        }

        String filename = file.getOriginalFilename();
        if (filename == null || filename.trim().isEmpty()) {
            throw new RuntimeException("Filename is empty");
        }

        if (!"xlsx".equalsIgnoreCase(getExtension(filename))) {
            throw new RuntimeException("File is not excel");
        }


        String contentType = file.getContentType();
        if (contentType == null || contentType.isBlank()) {
            throw new RuntimeException("ContentType is empty");
        }

        String ct = contentType.toLowerCase().trim();
        if (!ct.equals(XLSX_MIME_TYPE)) {
            throw new RuntimeException("ContentType is not excel");
        }
    }

    private static String getExtension(String filename) {
        if (filename == null) {
            return null;
        }
        int lastDot = filename.lastIndexOf('.');
        if (lastDot < 0 || lastDot == filename.length() - 1) {
            return null;
        }
        return filename.substring(lastDot + 1).trim();
    }

    public static Long parseLongExcel(String value) {
        if (value == null || value.isEmpty()) return null;

        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return (long) Double.parseDouble(value);
        }
    }
}
