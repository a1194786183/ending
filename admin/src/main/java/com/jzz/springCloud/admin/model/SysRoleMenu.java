package com.jzz.springCloud.admin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * sys_role_menu
 *
 * @author liangshanguang
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleMenu extends BaseModel {
    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 菜单id
     */
    private Long menuId;
}