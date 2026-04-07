package com.devteria.identity_service.base.excelHandling.controller;

import com.devteria.identity_service.base.excelHandling.base.ExportExcelBase;
import com.devteria.identity_service.user.dto.response.UserResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DemoExportExcelController {
    @GetMapping("/api/demo/export/users")
    public void exportUsers(HttpServletResponse response) throws IOException {
        List<UserResponse> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserResponse user = new UserResponse();
            user.setId(String.valueOf(i));
            user.setEmail("email" + i);
            user.setFirstName("firstName" + i);
            user.setLastName("lastName" + i);

            users.add(user);
        }

        String[] headers = {"ID", "Email", "First Name", "Last Name"};
        String[] fields = {"id", "email", "firstName", "lastName"};

        ExportExcelBase<UserResponse> exporter = new ExportExcelBase<>(users);

        // Set header response
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=users.xlsx");

        exporter
                .writeHeaderLine(headers)
                .writeDataLines(fields, UserResponse.class)
                .export(response);
    }

    @GetMapping("/api/demo/export-b64/users")
    public void exportUsersBase64(HttpServletResponse response) throws IOException {
        List<UserResponse> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserResponse user = new UserResponse();
            user.setId(String.valueOf(i));
            user.setEmail("email" + i);
            user.setFirstName("firstName" + i);
            user.setLastName("lastName" + i);

            users.add(user);
        }

        String[] headers = {"ID", "Email", "First Name", "Last Name"};
        String[] fields = {"id", "email", "firstName", "lastName"};

        ExportExcelBase<UserResponse> exporter = new ExportExcelBase<>(users);

        // Set header response
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=users.xlsx");

        exporter
                .writeHeaderLine(headers)
                .writeDataLines(fields, UserResponse.class)
                .exportBase64(response);
    }
}