package com.nhnacademy.board.controller.admin;

import com.nhnacademy.board.domain.User;
import com.nhnacademy.board.request.UserRegisterRequest;
import com.nhnacademy.board.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/user")
public class AdminController{
    UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String admin(@RequestParam(name = "page")int page, Model model){
        List<User> userList = userService.getUserList();
        int last = userList.size()/10;
        List<User> userPartList = userService.getPartList(page);
        model.addAttribute("userList",userPartList);
        if(last==1){
            return "user/userList";
        }
        if(page==1){
            model.addAttribute("nextPage",2);
        }
        else if(page==last){
            model.addAttribute("prePage",page-1);
        }
        else{
            model.addAttribute("prePage",page-1);
            model.addAttribute("nextPage",page+1);
        }
        return "user/userList";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute(new User());
        return "user/userRegister";
    }

    @PostMapping("/register")
    public String addRegister(@ModelAttribute UserRegisterRequest userRegisterRequest) throws IOException {
        userService.register(userRegisterRequest);
        return "redirect:/user?page=1";
    }


    @GetMapping("/view")
    public String views(@RequestParam(name = "id") String id, Model model){
        User user = userService.getUser(id);

        model.addAttribute("user",user);
        return "user/userView";
    }
    @PostMapping("/delete")
    public String delete(@RequestParam(name = "id") String id){
        userService.delete(id);
        return "redirect:/user?page=1";
    }
    @GetMapping("/update")
    public String getUpdate(@RequestParam(name = "id")String id,Model model){
        User user = userService.getUser(id);
        model.addAttribute("user",user);
        return "user/userRegister";
    }
    @PostMapping("/update")
    public String update(UserRegisterRequest userRegisterRequest, Model model) throws IOException {
        userService.modify(userRegisterRequest);
        return "redirect:/user?page=1";
    }

}
