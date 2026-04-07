package com.devteria.identity_service.excelHandling.service;


import com.devteria.identity_service.excelHandling.base.UploadExcelBase;
import com.devteria.identity_service.items.dto.ItemsCreateReq;
import com.devteria.identity_service.items.repository.ItemsRepo;
import com.devteria.identity_service.items.service.ItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImportItemService {
    private final UploadExcelBase uploadExcelBase;

    private final ItemsService itemsService;
    private final ItemsRepo itemsRepo;


    public void importItem(MultipartFile file) throws Exception {
        List<List<String>> data = uploadExcelBase.readFileExcel(file);

        List<ItemsCreateReq> itemDTOs = new ArrayList<>();
        int rowIdx = 0;
        for (List<String> row : data) {

            if (rowIdx == 0) {
                rowIdx += 1;
                continue;
            }

            ItemsCreateReq itemDto = new ItemsCreateReq();
            itemDto.setGroupId(UploadExcelBase.parseLongExcel(row.get(0)));
            itemDto.setCode(row.get(1));
            itemDto.setName(row.get(2));
            itemDto.setUnit(row.get(3));
            itemDto.setCodeErp(row.get(4));

            itemDTOs.add(itemDto);

            rowIdx += 1;
        }
        
        itemsService.createMultiItems(itemDTOs);
    }
}
