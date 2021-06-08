package com.koreait.spring.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insUser(UserEntity param);//파라미터 보내줌
    UserEntity selUser(UserEntity param);
    int updUser(UserEntity param);
}
