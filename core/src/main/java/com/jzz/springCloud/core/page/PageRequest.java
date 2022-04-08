package com.jzz.springCloud.core.page;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageRequest {
    /**
     * 当前页码
     */
    private int pageNum;

    /**
     * 每页数量
     */
    private int pageSize;

    /**
     * 查询参数
     */
    private List<Param> params = new ArrayList<>();

    /**
     * 查询参数对象
     *
     * @param name Param的value
     * @return 指定的键值对对象
     */
    public Param getParam(String name) {
        for (Param param : this.params) {
            if (name != null && name.equals(param.getName())) {
                return param;
            }
        }
        return null;
    }

    /**
     * 查询参数值
     *
     * @param name 参数名称
     * @return 参数值
     */
    public String getParamValue(String name) {
        Param param = getParam(name);
        if (param != null) {
            return param.getValue();
        }
        return null;
    }
}
