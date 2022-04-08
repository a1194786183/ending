package com.jzz.springCloud.admin.service.impl;

import com.jzz.springCloud.admin.constant.SysConstants;
import com.jzz.springCloud.admin.mapper.SysMenuMapper;
import com.jzz.springCloud.admin.mapper.SysRoleMapper;
import com.jzz.springCloud.admin.mapper.SysRoleMenuMapper;
import com.jzz.springCloud.admin.model.SysMenu;
import com.jzz.springCloud.admin.model.SysRole;
import com.jzz.springCloud.admin.model.SysRoleMenu;
import com.jzz.springCloud.admin.service.SysRoleService;
import com.jzz.springCloud.core.page.MyBatisPageHelper;
import com.jzz.springCloud.core.page.PageRequest;
import com.jzz.springCloud.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysRole> findAll() {
        return sysRoleMapper.findAll();
    }

    @Override
    public List<SysMenu> findRoleMenus(Long roleId) {
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(roleId);
        if (SysConstants.ADMIN.equalsIgnoreCase(sysRole.getName())) {
            // 如果是超级管理员，返回全部
            return sysMenuMapper.findAll();
        }
        return sysMenuMapper.findRoleMenus(roleId);
    }

    @Override
    public int saveRoleMenus(List<SysRoleMenu> records) {
        if (records == null || records.isEmpty()) {
            return 1;
        }
        Long roleId = records.get(0).getRoleId();
        sysRoleMenuMapper.deleteByRoleId(roleId);
        for (SysRoleMenu record : records) {
            sysRoleMenuMapper.insertSelective(record);
        }
        return 1;
    }

    @Override
    public List<SysRole> findByName(String name) {
        return sysRoleMapper.findByName(name);
    }

    @Override
    public int save(SysRole record) {
        if (record.getId() == null || record.getId() == 0) {
            return sysRoleMapper.insertSelective(record);
        }
        return sysRoleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysRole record) {
        return sysRoleMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysRole> records) {
        for (SysRole record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public SysRole findById(Long id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object label = pageRequest.getParamValue("name");
        if (label != null) {
            return MyBatisPageHelper.findPage(pageRequest, sysRoleMapper, "findPageByName", label);
        }
        return MyBatisPageHelper.findPage(pageRequest, sysRoleMapper);
    }
}
