package com.nhnacademy.board.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.validation.Valid;

@Getter
@Valid
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegisterRequest {

    private String id;
    private String password;
    private String name;

    private String profileFileName;

    private MultipartFile profileFile;

    public UserRegisterRequest(String id, String password, String name, String profileFileName) {
        this.id =id;
        this.password= password;
        this.name = name;
        this.profileFileName = profileFileName;
    }
     @Override
    public String toString() {
        return "StudentRegisterRequest{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
