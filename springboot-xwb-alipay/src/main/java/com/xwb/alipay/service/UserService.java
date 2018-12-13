package com.xwb.alipay.service;

import com.vbao.alipay.model.User;

import java.util.List;

public interface UserService {

    User selectOne(Long id);

    List<User> selectAll();

    Long insert(User user);


}
