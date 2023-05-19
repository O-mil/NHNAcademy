package com.nhnacademy.board.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="Users")
public class User implements IUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private String id;
    @Column(name="user_pw")
    private String password;
    @Column(name="user_name")
    private String name;
    @Column(name="filename")
    private String profileFileName;

    public User(String id, String password, String name, String profileFileName) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.profileFileName = profileFileName;
    }

    public User(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    public static User createUser(String id, String password, String name, String profileFileName) {
        return new User(id, password, name, profileFileName);
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", profileFileName='" + profileFileName + '\'' +
                '}';
    }
}
