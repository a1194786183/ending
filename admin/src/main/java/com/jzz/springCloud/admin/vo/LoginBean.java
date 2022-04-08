package com.jzz.springCloud.admin.vo;

import lombok.Data;

@Data
public class LoginBean {
    String account;
    String password;
    String captcha;
}
