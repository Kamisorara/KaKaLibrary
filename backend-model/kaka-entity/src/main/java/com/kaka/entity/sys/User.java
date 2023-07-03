package com.kaka.entity.sys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    // 用户id
    private Long id;
    // 用户名
    private String userName;
    // 密码
    private String password;
    // 邮箱
    private String email;
    // 手机号
    private String phoneNumber;
    // 管理员状态
    private String admin;
    // 账号启用状态
    private String status;
    // 账号创建时间
    private Date createTime;

}
