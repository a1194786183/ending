package com.jzz.springCloud.admin.controller;

import com.jzz.springCloud.admin.model.SysLoginLog;
import com.jzz.springCloud.admin.service.SysLoginLogService;
import com.jzz.springCloud.core.http.HttpResult;
import com.jzz.springCloud.core.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("loginlog")
@Api(tags = "登录日志接口")
public class SysLoginLogController {
    @Autowired
    private SysLoginLogService sysLoginLogService;

    @PostMapping("/findPage")
    @ApiOperation("获取所有登录日志并进行分页")
    @PreAuthorize("hasAuthority('sys:loginlog:view')")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(sysLoginLogService.findPage(pageRequest));
    }

    @PostMapping("/delete")
    @ApiOperation("删除登录日志")
    @PreAuthorize("hasAuthority('sys:loginlog:delete')")
    public HttpResult delete(@RequestBody List<SysLoginLog> records) {
        return HttpResult.ok(sysLoginLogService.delete(records));
    }
}
