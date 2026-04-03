package com.devteria.identity_service.exportDocument.controller;


import com.devteria.identity_service.exportDocument.dto.ExportRequestDTO;
import com.devteria.identity_service.exportDocument.dto.ExportResponseDTO;
import com.devteria.identity_service.exportDocument.service.ExportDocumentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/document")
public class ExportDocumentController {
    private final ExportDocumentService exportDocumentService;
    @PostMapping("/export")
    public ResponseEntity<ExportResponseDTO>  exportDocument(@RequestBody ExportRequestDTO exportRequestDTO) {
        ExportResponseDTO response = exportDocumentService.exportDocument(exportRequestDTO);
        return ResponseEntity.ok(response);
    }
}
