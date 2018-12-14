package com.xwb.alipay.service;

import com.xwb.alipay.mapper.UserMapper;
import com.xwb.alipay.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User selectOne(Long id) {
        return userMapper.selectOne(id);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public Long insert(User user) {
        return userMapper.insert(user);
    }
}
