package com.nhnacademy.exam.parser;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DepartmentParserResolver {

    private final List<DepartmentParser> departmentParserList;


    public DepartmentParser getDepartmentParser(String fileName){
        for (DepartmentParser parser : departmentParserList) {
            if (parser.matchFileType(fileName)) {
                return parser;
            }
        }
        return null;
    }

}
