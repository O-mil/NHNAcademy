package com.nhnacademy.exam.parser.impl;

import com.nhnacademy.exam.dto.DepartmentDTO;
import com.nhnacademy.exam.parser.DepartmentParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CsvDepartmentParser implements DepartmentParser {

    @Override
    public String getFileType() {
        return ".csv";
    }

    @Override
    public List<DepartmentDTO> parsing(File file) throws IOException {
        List<DepartmentDTO> departmentList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    String departmentCode = data[3].trim();
                    String departmentName = data[2].trim();

                    DepartmentDTO departmentDTO = new DepartmentDTO(departmentCode, departmentName);
                    departmentList.add(departmentDTO);
                }
            }
        }

        return departmentList;
    }
}