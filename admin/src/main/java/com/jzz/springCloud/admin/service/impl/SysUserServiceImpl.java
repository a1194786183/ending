package com.jzz.springCloud.admin.service.impl;

import com.jzz.springCloud.admin.mapper.SysRoleMapper;
import com.jzz.springCloud.admin.mapper.SysUserMapper;
import com.jzz.springCloud.admin.mapper.SysUserRoleMapper;
import com.jzz.springCloud.admin.model.SysMenu;
import com.jzz.springCloud.admin.model.SysRole;
import com.jzz.springCloud.admin.model.SysUser;
import com.jzz.springCloud.admin.model.SysUserRole;
import com.jzz.springCloud.admin.service.SysMenuService;
import com.jzz.springCloud.admin.service.SysUserService;
import com.jzz.springCloud.common.utils.DateTimeUtils;
import com.jzz.springCloud.common.utils.PoiUtils;
import com.jzz.springCloud.core.page.MyBatisPageHelper;
import com.jzz.springCloud.core.page.PageRequest;
import com.jzz.springCloud.core.page.PageResult;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.findAll();
    }

    @Override
    public SysUser findByName(String userName) {
        return sysUserMapper.findByName(userName);
    }

    @Override
    public Set<String> findPermissions(String userName) {
        Set<String> perms = new HashSet<>();
        List<SysMenu> sysMenus = sysMenuService.findByUser(userName);
        for (SysMenu sysMenu : sysMenus) {
            if (sysMenu.getPerms() != null && !"".equals(sysMenu.getPerms())) {
                perms.add(sysMenu.getPerms());
            }
        }
        return perms;
    }

    @Override
    public List<SysUserRole> findUserRoles(Long userId) {
        return sysUserRoleMapper.findUserRoles(userId);
    }

    @Override
    public File createUserExcelFile(PageRequest pageRequest) {
        PageResult pageResult = findPage(pageRequest);
        return createUserExcelFile(pageResult.getContent());
    }

    public static File createUserExcelFile(List<?> records) {
        if (records == null) {
            records = new ArrayList<>();
        }
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row0 = sheet.createRow(0);
        int columnIndex = 0;
        row0.createCell(columnIndex).setCellValue("No");
        row0.createCell(++columnIndex).setCellValue("ID");
        row0.createCell(++columnIndex).setCellValue("?????????");
        row0.createCell(++columnIndex).setCellValue("??????");
        row0.createCell(++columnIndex).setCellValue("??????");
        row0.createCell(++columnIndex).setCellValue("??????");
        row0.createCell(++columnIndex).setCellValue("??????");
        row0.createCell(++columnIndex).setCellValue("?????????");
        row0.createCell(++columnIndex).setCellValue("??????");
        row0.createCell(++columnIndex).setCellValue("??????");
        row0.createCell(++columnIndex).setCellValue("?????????");
        row0.createCell(++columnIndex).setCellValue("????????????");
        row0.createCell(++columnIndex).setCellValue("???????????????");
        row0.createCell(++columnIndex).setCellValue("??????????????????");
        for (int i = 0; i < records.size(); i++) {
            SysUser user = (SysUser) records.get(i);
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < columnIndex + 1; j++) {
                row.createCell(j);
            }
            columnIndex = 0;
            row.getCell(columnIndex).setCellValue(i + 1);
            row.getCell(++columnIndex).setCellValue(user.getId());
            row.getCell(++columnIndex).setCellValue(user.getName());
            row.getCell(++columnIndex).setCellValue(user.getNickName());
            row.getCell(++columnIndex).setCellValue(user.getDeptName());
            row.getCell(++columnIndex).setCellValue(user.getRoleNames());
            row.getCell(++columnIndex).setCellValue(user.getEmail());
            row.getCell(++columnIndex).setCellValue(user.getStatus());
            row.getCell(++columnIndex).setCellValue(user.getAvatar());
            row.getCell(++columnIndex).setCellValue(user.getCreateBy());
            row.getCell(++columnIndex).setCellValue(DateTimeUtils.getDateTime(user.getCreateTime()));
            row.getCell(++columnIndex).setCellValue(user.getLastUpdateBy());
            row.getCell(++columnIndex).setCellValue(DateTimeUtils.getDateTime(user.getLastUpdateTime()));
        }
        return PoiUtils.createExcelFile(workbook, "download_user");
    }

    @Override
    public int save(SysUser record) {
        Long id = null;
        if (record.getId() == null || record.getId() == 0) {
            // ????????????
            sysUserMapper.insertSelective(record);
            id = record.getId();
        } else {
            // ??????????????????
            sysUserMapper.updateByPrimaryKeySelective(record);
        }
        // ??????????????????
        if (id != null && id == 0) {
            return 1;
        }

        List<SysUserRole> userRoles = record.getUserRoles();
        if (id != null) {
            for (SysUserRole userRole : userRoles) {
                userRole.setUserId(id);
            }
        } else {
            sysUserRoleMapper.deleteByUserId(record.getId());
        }
        for (SysUserRole userRole : userRoles) {
            sysUserRoleMapper.insertSelective(userRole);
        }
        return 1;
    }

    @Override
    public int delete(SysUser record) {
        return sysUserMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysUser> records) {
        for (SysUser record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public SysUser findById(Long id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        PageResult pageResult = null;
        Object name = pageRequest.getParamValue("name");
        Object email = pageRequest.getParamValue("email");
        if (name != null) {
            if (email != null) {
                pageResult = MyBatisPageHelper.findPage(pageRequest, sysUserMapper, "findPageByNameAndEmail", name, email);
            } else {
                pageResult = MyBatisPageHelper.findPage(pageRequest, sysUserMapper, "findPageByName", name);
            }
        } else {
            pageResult = MyBatisPageHelper.findPage(pageRequest, sysUserMapper);
        }
        // ????????????????????????
        getUserRoles(pageResult);
        return pageResult;
    }

    /**
     * ??????????????????
     *
     * @param pageResult ?????????????????????
     */
    private void getUserRoles(PageResult pageResult) {
        List<?> content = pageResult.getContent();
        for (Object object : content) {
            SysUser sysUser = (SysUser) object;
            // ???????????????????????????????????????
            List<SysUserRole> userRoles = findUserRoles(sysUser.getId());
            // ????????????????????????
            sysUser.setUserRoles(userRoles);
            // ???????????????????????????????????????
            sysUser.setRoleNames(getRoleNames(userRoles));
        }
    }

    /**
     * ?????????????????????????????????
     *
     * @param userRoles ????????????????????????
     * @return ???????????????????????????
     */
    private String getRoleNames(List<SysUserRole> userRoles) {
        StringBuilder sb = new StringBuilder();
        for (Iterator<SysUserRole> iter = userRoles.iterator(); iter.hasNext(); ) {
            SysUserRole userRole = iter.next();
            // ??????????????????
            SysRole sysRole = sysRoleMapper.selectByPrimaryKey(userRole.getRoleId());
            // ?????????????????????????????????
            if (sysRole == null) {
                continue;
            }
            // ????????????????????????????????????????????????
            sb.append(sysRole.getRemark());
            if (iter.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
