package com.jzz.springCloud.admin.controller;

import com.jzz.springCloud.admin.model.SysUser;
import com.jzz.springCloud.admin.service.SysGetUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author jzz
 * @create 2022/4/19
 */
public class SysUserInfoController {
    @Autowired
    SysGetUserInfoService sysGetUserInfoService;

    @GetMapping("consumer/getInfo")
    public SysUser getUserInfo(@AuthenticationPrincipal SysUser user){
        return user;
    }
}
