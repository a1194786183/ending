package com.jzz.springCloud.admin.service;

import com.jzz.springCloud.admin.model.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author jzz
 * @create 2022/4/19
 */
@Component
@FeignClient(name="CLOUD-USER-SERVICE")
public interface SysGetUserInfoService{
    @GetMapping("/provider/getInfo")
    SysUser getUserInfo(SysUser user);
}
