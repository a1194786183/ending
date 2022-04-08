package com.jzz.springCloud.admin.controller;

import com.jzz.springCloud.admin.constant.SysConstants;
import com.jzz.springCloud.admin.mapper.SysRoleMapper;
import com.jzz.springCloud.admin.model.SysRole;
import com.jzz.springCloud.admin.model.SysRoleMenu;
import com.jzz.springCloud.admin.service.SysRoleService;
import com.jzz.springCloud.core.http.HttpResult;
import com.jzz.springCloud.core.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@Api(tags = "用户角色接口")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @PostMapping("/save")
    @ApiOperation("修改角色")
    @PreAuthorize("hasAuthority('sys:role:add') AND hasAuthority('sys:role:edit')")
    public HttpResult save(@RequestBody SysRole record) {
        SysRole role = sysRoleService.findById(record.getId());
        if (role != null) {
            if (SysConstants.ADMIN.equalsIgnoreCase(role.getName())) {
                return HttpResult.error("超级管理员不允许修改!");
            }
        }
        // 新增角色
        List<SysRole> sysRoles = sysRoleService.findByName(record.getName());
        if ((record.getId() == null || record.getId() == 0) && !sysRoles.isEmpty()) {
            return HttpResult.error("角色名已存在!");
        }
        return HttpResult.ok(sysRoleService.save(record));
    }

    @PostMapping("/delete")
    @ApiOperation("删除角色")
    @PreAuthorize("hasAuthority('sys:role:delete')")
    public HttpResult delete(@RequestBody List<SysRole> records) {
        return HttpResult.ok(sysRoleService.delete(records));
    }

    @PostMapping("/findPage")
    @ApiOperation("拿到角色分页列表")
    @PreAuthorize("hasAuthority('sys:role:view')")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(sysRoleService.findPage(pageRequest));
    }

    @GetMapping("/findAll")
    @ApiOperation("获取所有的角色列表")
    @PreAuthorize("hasAuthority('sys:role:view')")
    public HttpResult findAll() {
        return HttpResult.ok(sysRoleService.findAll());
    }

    @GetMapping("/findRoleMenus")
    @ApiOperation("获取指定角色的可访问菜单")
    @PreAuthorize("hasAuthority('sys:role:view')")
    public HttpResult findRoleMenus(@RequestParam Long roleId) {
        return HttpResult.ok(sysRoleService.findRoleMenus(roleId));
    }

    @PostMapping("/saveRoleMenus")
    @ApiOperation("更新指定角色的可访问菜单")
    @PreAuthorize("hasAuthority('sys:role:edit')")
    public HttpResult saveRoleMenus(@RequestBody List<SysRoleMenu> records) {
        for (SysRoleMenu record : records) {
            SysRole sysRole = sysRoleMapper.selectByPrimaryKey(record.getRoleId());
            if (SysConstants.ADMIN.equalsIgnoreCase(sysRole.getName())) {
                // 如果是超级管理员，不允许修改
                return HttpResult.error("超级管理员拥有所有菜单权限，不允许修改！");
            }
        }
        return HttpResult.ok(sysRoleService.saveRoleMenus(records));
    }
}
