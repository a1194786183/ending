package com.jzz.springCloud.admin.aspect;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.jzz.springCloud.admin.security.SecurityUtils;
import com.jzz.springCloud.common.utils.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Configuration
public class MapperAspect {
    private static final String CREATE_BY = "createBy";
    private static final String CREATE_TIME = "createTime";
    private static final String LAST_UPDATE_BY = "lastUpdateBy";
    private static final String LAST_UPDATE_TIME = "lastUpdateTime";

    @Pointcut("execution(* com.jzz.springCloud.*.mapper.*.update*(..))")
    public void daoUpdate() {
    }

    @Pointcut("execution(* com.jzz.springCloud.*.mapper.*.insert*(..))")
    public void daoCreate() {
    }

    @Around("daoUpdate()")
    public Object doAroundUpdate(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return pjp.proceed();
        }
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("token");
        String username = getUserName();
        if (token != null && username != null) {
            Object[] objects = pjp.getArgs();
            if (objects != null && objects.length > 0) {
                for (Object arg : objects) {
                    BeanUtils.setProperty(arg, LAST_UPDATE_BY, username);
                    BeanUtils.setProperty(arg, LAST_UPDATE_TIME, new Date());
                }
            }
        }
        Object object = pjp.proceed();
        return object;

    }

    @Around("daoCreate()")
    public Object doAroundCreate(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return pjp.proceed();
        }
        Object[] objects = pjp.getArgs();
        if (objects != null && objects.length > 0) {
            for (Object arg : objects) {
                String username = getUserName();
                if (username != null) {
                    if (StringUtils.isBlank(BeanUtils.getProperty(arg, CREATE_BY))) {
                        BeanUtils.setProperty(arg, CREATE_BY, username);
                    }
                    if (StringUtils.isBlank(BeanUtils.getProperty(arg, CREATE_TIME))) {
                        BeanUtils.setProperty(arg, CREATE_TIME, new Date());
                    }
                }
            }
        }
        Object object = pjp.proceed();
        return object;
    }

    private String getUserName() {
        return SecurityUtils.getUsername();
    }
}

