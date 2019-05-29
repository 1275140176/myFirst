package com.itkun.service;

import com.itkun.entity.User;

public interface UserService {

    public User findByName(String name);
    public User findById(Integer id);
}
