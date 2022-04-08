package com.jzz.springCloud.admin.service;

import com.jzz.springCloud.admin.model.SysLoginLog;
import com.jzz.springCloud.core.service.CurdService;

public interface SysLoginLogService extends CurdService<SysLoginLog> {
    /**
     * 记录登录日志
     *
     * @param username 登录用户名
     * @param ipAddr   登录者的ip
     * @return 执行成功与否的错误码
     */
    int writeLoginLog(String username, String ipAddr);
}
