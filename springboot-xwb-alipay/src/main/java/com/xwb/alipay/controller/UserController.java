package com.xwb.alipay.controller;

import com.xwb.alipay.model.User;
import com.xwb.alipay.service.UserService;
import com.xwb.alipay.utils.ListUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(value="/user", tags="user接口模块")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value="添加用户信息", notes = "添加用户信息")
    @ApiImplicitParam(name="user", value="User", required = true, dataType = "User")
   @RequestMapping("/getUser")
    public User getUser(){
        return new User();
    }



    @RequestMapping(value = "/toIndex")
    public String toIndex(ModelMap model){
        User user = new User();
        user.setAge(17);
        user.setId(3L);
        user.setName("程英");
        model.addAttribute("user",user);
        return "index";
    }



    @RequestMapping(value = "/selectOne/{id}")
    @ResponseBody
    public User selectOne(@PathVariable("id") Long id){
        User user = userService.selectOne(id);
        return user;
    }

    @RequestMapping(value = "/toMap")
    @ResponseBody
    public Map<Object,User> toMap(){
        List<User> userList = userService.selectAll();
        Map<Object, User> userMap = ListUtil.listToMap(userList, "name", User.class);
        User user = userMap.get("小龙女");
        return userMap;
    }






}
