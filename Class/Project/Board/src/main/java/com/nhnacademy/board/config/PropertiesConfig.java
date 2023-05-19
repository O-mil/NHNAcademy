package com.nhnacademy.board.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Set;

// TODO #2: Java Config 파일 - @PropertySource 를 통해 properties 파일 가져오기
@Configuration
@PropertySource("classpath:common.properties")
public class PropertiesConfig {

    @Getter
    @Value("#{'${excludeUrls}'.split(',')}")
    private Set<String> excludeUrls;

    @Value("/Users/yoonmin/Desktop/java_backend/backend-study/JDBC/5.4_/board_task/board/src/main/upload")
    public String uploadPath;

    public Set<String> getExcludeUrls() { return excludeUrls;}

    public String getUploadPath(){
        return uploadPath;
    }

}
