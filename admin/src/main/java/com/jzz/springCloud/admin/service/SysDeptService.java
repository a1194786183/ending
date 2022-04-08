package com.jzz.springCloud.admin.service;

import com.jzz.springCloud.admin.model.SysDept;
import com.jzz.springCloud.core.service.CurdService;

import java.util.List;

public interface SysDeptService extends CurdService<SysDept> {
    /**
     * 查询部门树
     *
     * @return 部门树
     */
    List<SysDept> findTree();
}
