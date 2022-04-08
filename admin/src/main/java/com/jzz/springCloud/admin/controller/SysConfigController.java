package com.jzz.springCloud.admin.controller;

import com.jzz.springCloud.admin.model.SysConfig;
import com.jzz.springCloud.admin.service.SysConfigService;
import com.jzz.springCloud.core.http.HttpResult;
import com.jzz.springCloud.core.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/config")
@Api(tags = "系统配置接口")
public class SysConfigController {
    @Autowired
    private SysConfigService sysConfigService;

    @PostMapping("/save")
    @ApiOperation("保存配置")
    @PreAuthorize("hasAuthority('sys:config:add') AND hasAuthority('sys:config:edit')")
    public HttpResult save(@RequestBody SysConfig record) {
        return HttpResult.ok(sysConfigService.save(record));
    }

    @PostMapping("/delete")
    @ApiOperation("删除配置")
    @PreAuthorize("hasAuthority('sys:config:delete')")
    public HttpResult delete(@RequestBody List<SysConfig> records) {
        return HttpResult.ok(sysConfigService.delete(records));
    }

    @PostMapping("/findPage")
    @ApiOperation("获取配置的分页列表")
    @PreAuthorize("hasAuthority('sys:config:view')")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(sysConfigService.findPage(pageRequest));
    }

    @GetMapping("/findByLabel")
    @ApiOperation("根据配置名获取配置分页对象")
    @PreAuthorize("hasAuthority('sys:config:view')")
    public HttpResult findByLabel(@RequestParam String label) {
        return HttpResult.ok(sysConfigService.findByLabel(label));
    }
}
