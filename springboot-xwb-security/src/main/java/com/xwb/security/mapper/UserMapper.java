package com.xwb.security.mapper;

import com.xwb.security.model.User;

import java.util.List;

public interface UserMapper {

    User selectOne(Long id);

    List<User> selectAll();

    Long insert(User user);


}
