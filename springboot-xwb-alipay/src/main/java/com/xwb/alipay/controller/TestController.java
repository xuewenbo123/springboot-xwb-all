package com.xwb.alipay.controller;

import com.xwb.alipay.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/user")
public class TestController {

    /**
     * @param user
     * @return
     */
    @RequestMapping(value = "/application_json_post",method = RequestMethod.POST)
    public String application_json_post(@RequestBody User user){
        if(user == null){
            return "application_json_post ===== failure !";
        }
        System.out.println("application_json_post:  user.id is ===="+user.getId());
        System.out.println("application_json_post:  user.name is ====="+user.getName());
        return "SUCCESS ! ";
    }


    /**
     * 使用@ModelAttribute注解获取POST请求的FORM表单数据,@ModelAttribute("user")去掉也可以
     * @param user
     * @return
     */
    @RequestMapping(value = "/form_data_post_user",method = RequestMethod.POST)
    public String form_data_post_user(@ModelAttribute("user") User user){
        if(user == null){
            return "form_data_get ===== failure !";
        }
        System.out.println("form_data_post:  user.id is ===="+user.getId());
        System.out.println("form_data_post:  user.name is ====="+user.getName());
        return "SUCCESS ! ";
    }



    /**
     * form-data传输文件，post请求
     * @param file
     * @return
     */
    @RequestMapping(value = "/form_data_post_file",method = RequestMethod.POST)
    public String form_data_post_file(@RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            return "failure !";
        }
        return "SUCCESS ! ";
    }


    /**
     * post请求，x-www-form-urlencoded格式
     * @param
     * @return
     */
    @RequestMapping(value = "/x_www_form_urlencoded_Post",method = RequestMethod.POST,consumes = "application/x-www-form-urlencoded")
    public String x_www_form_urlencoded(User user){
        if(user == null){
            return "form_data_get ===== failure !";
        }
        System.out.println("form_data_post:  user.id is ===="+user.getId());
        System.out.println("form_data_post:  user.name is ====="+user.getName());
        return "SUCCESS ! ";
    }

//    <?xml version="1.0" encoding="UTF-8"?>
//    <user>
//        <id>1</id>
//        <name>疯狂Java讲义</name>
//    </user>


    /**
     *  xml传输
     * @param user
     * @return
     */
    @RequestMapping(value = "/application_xml",method = RequestMethod.POST,consumes = {MediaType.APPLICATION_XML_VALUE})
    public String application_xml(@RequestBody User user){
        if(user == null){
            return "form_data_get ===== failure !";
        }
        System.out.println("form_data_post:  user.id is ===="+user.getId());
        System.out.println("form_data_post:  user.name is ====="+user.getName());
        return "SUCCESS ! ";
    }

    @RequestMapping(value = "/return_xml",method = RequestMethod.POST,produces = {MediaType.APPLICATION_XML_VALUE})
    public User return_xml(@RequestBody User user){
        System.out.println("user.id is ===="+user.getId());
        System.out.println("user.name is ====="+user.getName());
        return user;
    }



    /**
     * get请求，数据格式为
     * @param user
     * @return
     */
    @RequestMapping(value = "/test_get",method = RequestMethod.GET)
    public String application_json_get( User user){
        if(user == null){
            return "application_json_get ===== failure !";
        }
        System.out.println("application_json_get:  user.id is ===="+user.getId());
        System.out.println("application_json_get:  user.name is ====="+user.getName());
        return "SUCCESS ! ";
    }





    @RequestMapping("/test_http")
    public String test_http(HttpServletRequest request){
        String id = request.getParameter("id");
        String name =request.getParameter("name");
        System.out.println("===========id=========="+id);
        System.out.println("==========name========="+name);
        return "SUCCESS ! ";
    }





}
