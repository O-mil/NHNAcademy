package com.nhnacademy.exam.parser.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.nhnacademy.exam.dto.DepartmentDTO;
import com.nhnacademy.exam.parser.DepartmentParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class JsonDepartmentParser implements DepartmentParser {

    private final ObjectMapper objectMapper;

    @Override
    public String getFileType() {
        return "json";
    }


    @Override
    public List<DepartmentDTO> parsing(File file) throws IOException {
        List<DepartmentDTO> departmentList = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(file);

        if (rootNode.isArray()) {
            for (JsonNode node : rootNode) {
                String departmentCode = node.get("부서코드").asText().trim();
                String departmentName = node.get("부서").asText().trim();

                DepartmentDTO departmentDTO = new DepartmentDTO(departmentCode, departmentName);
                departmentList.add(departmentDTO);
            }
        }
        return departmentList;
    }
}
