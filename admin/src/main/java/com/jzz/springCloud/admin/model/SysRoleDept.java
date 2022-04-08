package com.jzz.springCloud.admin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * sys_role_dept
 *
 * @author liangshanguang
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleDept extends BaseModel {
    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 机构id
     */
    private Long deptId;

}