package com.devteria.identity_service.exportDocument.service;


import com.devteria.identity_service.exportDocument.dto.DocumentContext;
import com.devteria.identity_service.exportDocument.dto.ExportRequestDTO;
import com.devteria.identity_service.exportDocument.dto.ExportResponseDTO;
import com.devteria.identity_service.exportDocument.template.TemplateStrategy;
import com.devteria.identity_service.exportDocument.template.TemplateStrategyFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExportDocumentService {
    private final TemplateStrategyFactory templateStrategyFactory;

    public ExportResponseDTO exportDocument(ExportRequestDTO exportRequestDTO) {
        DocumentContext context = DocumentContext.builder()
                .resId(exportRequestDTO.getResId())
                .resModel(exportRequestDTO.getResModel())
                .format(exportRequestDTO.getExportFormat())
                .templateType(exportRequestDTO.getTemplateType())
                .build();

        TemplateStrategy strategy = templateStrategyFactory
                .getTemplateStrategy(exportRequestDTO.getResModel(), exportRequestDTO.getTemplateType());

        strategy.populateData(context);
        byte[] content = strategy.render(context);

        return ExportResponseDTO.builder()
                .content(content)
                .resId(exportRequestDTO.getResId())
                .resModel(exportRequestDTO.getResModel())
                .fileName(context.getOutputFileName())
                .exportFormat(exportRequestDTO.getExportFormat())
                .build();
    }


}
