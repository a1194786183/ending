package com.jzz.springCloud.admin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * sys_user_role
 *
 * @author liangshanguang
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserRole extends BaseModel {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;
}