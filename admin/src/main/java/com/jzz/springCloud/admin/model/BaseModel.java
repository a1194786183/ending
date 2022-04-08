package com.jzz.springCloud.admin.model;

import lombok.Data;

import java.util.Date;

@Data
public class BaseModel {
    /**
     * 编号
     */
    private Long id;
    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String lastUpdateBy;

    /**
     * 更新时间
     */
    private Date lastUpdateTime;
}
