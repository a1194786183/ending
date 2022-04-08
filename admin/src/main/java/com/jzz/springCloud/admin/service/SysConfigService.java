package com.jzz.springCloud.admin.service;

import com.jzz.springCloud.admin.model.SysConfig;
import com.jzz.springCloud.core.service.CurdService;

import java.util.List;

public interface SysConfigService extends CurdService<SysConfig> {
    List<SysConfig> findByLabel(String label);
}
