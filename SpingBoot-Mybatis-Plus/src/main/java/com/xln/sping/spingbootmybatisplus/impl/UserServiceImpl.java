package com.xln.sping.spingbootmybatisplus.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xln.sping.spingbootmybatisplus.entity.User;
import com.xln.sping.spingbootmybatisplus.mapper.UserMapper;
import com.xln.sping.spingbootmybatisplus.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
