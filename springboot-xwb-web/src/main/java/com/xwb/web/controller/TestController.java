package com.xwb.web.controller;

import com.xwb.web.form.UserForm;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/test_application_json")
    public String test_application_json(@RequestBody UserForm userForm){
        if(userForm == null){
            return "failure !";
        }
        System.out.println("userForm.id is ===="+userForm.getId());
        System.out.println("userForm.userName is ====="+userForm.getUserName());
        return "SUCCESS ! ";
    }





}
