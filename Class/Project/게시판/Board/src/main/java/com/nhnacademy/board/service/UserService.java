package com.nhnacademy.board.service;

import com.ctc.wstx.util.StringUtil;
import com.nhnacademy.board.config.PropertiesConfig;
import com.nhnacademy.board.domain.User;
import com.nhnacademy.board.exception.DuplicateStudentIdException;
import com.nhnacademy.board.exception.StudentNotFoundException;
import com.nhnacademy.board.repository.IUserRepository;
import com.nhnacademy.board.request.UserRegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Primary
@Slf4j
@Service("userService")
public class UserService {
    private final IUserRepository userRepository;

    private final PropertiesConfig propertiesConfig;

    public UserService(IUserRepository userRepository, PropertiesConfig propertiesConfig) {
        this.propertiesConfig = propertiesConfig;
        this.userRepository = userRepository;
    }

    public User getUser(String id){
        User user =  userRepository.findById(id).orElse(null);
        if(Objects.isNull(user)){
            throw new StudentNotFoundException(id);
        }
        return user;
    }
    public void delete(String id){
        userRepository.deleteById(id);
    }

    public List<User> getUserList(){
        return userRepository.findAll();
    }
    public List<User> getPartList(int num){
        num = (num-1)*10;
        int size = 10;
        List<User> userList = new ArrayList<>();
        List<User> list = userRepository.findAll();
        if(list.size()<num+10){
            size=list.size()-num;
        }
        for(int i=0;i<10;i++){
            userList.add(list.get(num));
            num++;
        }
        return userList;
    }

    public void register(UserRegisterRequest userRegisterRequest){
        MultipartFile file = userRegisterRequest.getProfileFile();
        if(!file.isEmpty()) {
            try{
                file.transferTo(Paths.get(propertiesConfig.getUploadPath() + File.separator + file.getOriginalFilename()));
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
        }

        if(userRepository.findById(userRegisterRequest.getId()).isPresent()) {
            throw new DuplicateStudentIdException(userRegisterRequest.getId());
        }
        log.info("fileName:{}", userRegisterRequest.getProfileFile().getOriginalFilename());
        User user  = User.createUser(userRegisterRequest.getId(), userRegisterRequest.getPassword(), userRegisterRequest.getName(), userRegisterRequest.getProfileFileName());
        userRepository.save(user);

    }


    public void modify(UserRegisterRequest userRegisterRequest){
        User user = userRepository.findById(userRegisterRequest.getId()).orElse(null);
        String uploadPath = propertiesConfig.getUploadPath();
        MultipartFile file = userRegisterRequest.getProfileFile();
        if(!file.isEmpty()) {
            try{
                file.transferTo(Paths.get(uploadPath + File.separator + file.getOriginalFilename()));
                user.setProfileFileName(file.getOriginalFilename());
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
        }
        userRepository.save(user);
    }

    public String getProfileImagePath(String id) {
        User user = getUser(id);
        if (Objects.nonNull(user)) {
            if(StringUtils.hasText(user.getProfileFileName())){
                return user.getProfileFileName();
            }
        }
        return "no-image.png";
    }
}
