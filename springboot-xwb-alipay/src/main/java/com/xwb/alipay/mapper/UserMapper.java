package com.xwb.alipay.mapper;

import com.vbao.alipay.model.User;

import java.util.List;


public interface UserMapper {

    User selectOne(Long id);

    List<User> selectAll();

    Long insert(User user);

}