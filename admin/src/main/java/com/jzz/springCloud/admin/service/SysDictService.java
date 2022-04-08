package com.jzz.springCloud.admin.service;

import com.jzz.springCloud.admin.model.SysDict;
import com.jzz.springCloud.core.service.CurdService;

import java.util.List;

public interface SysDictService extends CurdService<SysDict> {
    /**
     * 根据label名进行查询
     *
     * @param label 标签名
     * @return 字典对象列表
     */
    List<SysDict> findByLabel(String label);
}
