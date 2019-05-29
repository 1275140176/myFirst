package com.itkun.mapper;

import com.itkun.entity.User;

public interface UserMapper {

    public User findByName(String name);
    public User findById(Integer id);
}
