package com.jzz.springCloud.admin.controller;

import com.jzz.springCloud.admin.model.SysDept;
import com.jzz.springCloud.admin.service.SysDeptService;
import com.jzz.springCloud.core.http.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dept")
@Api(tags = "部门管理接口")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    @PostMapping("/save")
    @ApiOperation("保存部门信息")
    @PreAuthorize("hasAuthority('sys:dept:add') AND hasAuthority('sys:dept:edit')")
    public HttpResult save(@RequestBody SysDept record) {
        return HttpResult.ok(sysDeptService.save(record));
    }

    @PostMapping("/delete")
    @ApiOperation("删除部门信息")
    @PreAuthorize("hasAuthority('sys:dept:delete')")
    public HttpResult delete(@RequestBody List<SysDept> records) {
        return HttpResult.ok(sysDeptService.delete(records));
    }

    @GetMapping("/findTree")
    @ApiOperation("获取部门树")
    @PreAuthorize("hasAuthority('sys:dept:view')")
    public HttpResult findTree() {
        return HttpResult.ok(sysDeptService.findTree());
    }

}
