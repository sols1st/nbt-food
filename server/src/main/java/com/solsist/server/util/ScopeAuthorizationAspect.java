package com.solsist.server.util;

import com.solsist.server.util.annotion.ScopeRequired;
import org.apache.shiro.authz.UnauthorizedException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ScopeAuthorizationAspect {

    @Before("@annotation(com.solsist.server.util.annotion.ScopeRequired)")
    public void authorizeScope(JoinPoint joinPoint) {
        // 获取当前认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 检查是否为JWT认证
        if (authentication instanceof JwtAuthenticationToken) {
            JwtAuthenticationToken jwtAuthentication = (JwtAuthenticationToken) authentication;

            // 获取JWT Token中的scope字段
            String scope = jwtAuthentication.getToken().getClaim("scope");

            // 获取注解上定义的所需权限
            try {
                ScopeRequired scopeRequired = joinPoint.getTarget().getClass()
                        .getMethod(joinPoint.getSignature().getName(), joinPoint.getArgs().getClass())
                        .getAnnotation(ScopeRequired.class);
                String[] requiredScopes = scopeRequired.value();
                // 鉴权逻辑：检查scope是否包含所需权限
                for (String requiredScope : requiredScopes) {
                    System.out.println(requiredScope);
                    System.out.println(scope);
                    if (!scope.contains(requiredScope)) {
                        throw new UnauthorizedException("Insufficient scope");
                    }
                }
            }catch (Exception e){

            }
        } else {
            throw new UnauthorizedException("Invalid authentication token");
        }
    }
}