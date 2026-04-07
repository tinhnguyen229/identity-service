package com.devteria.identity_service.base.excelHandling.controller;


import com.devteria.identity_service.base.excelHandling.service.ImportItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class DemoImportExcelController {
    private final ImportItemService importItemService;

    @PostMapping("/api/demo/item/import")
    public String importExcel(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new RuntimeException("No file");
        }

        importItemService.importItem(file);

        return "success";
    }
}
