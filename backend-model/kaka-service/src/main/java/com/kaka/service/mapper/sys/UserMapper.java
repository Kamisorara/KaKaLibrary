package com.kaka.service.mapper.sys;

import com.kaka.entity.sys.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
public interface UserMapper {

    User findUserById(@Param("userId") Long userId);
    User findUserByUserNameOrEmail(@Param("text") String text);
}
