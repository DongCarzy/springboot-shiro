package com.example.springbootshiro.shiro.realm;

import com.example.springbootshiro.exception.ResourceNotFoundException;
import com.example.springbootshiro.shiro.config.SysAdminProperties;
import com.example.springbootshiro.shiro.domain.Permission;
import com.example.springbootshiro.shiro.domain.Role;
import com.example.springbootshiro.shiro.domain.UserInfo;
import com.example.springbootshiro.shiro.service.PermissionService;
import com.example.springbootshiro.shiro.service.RoleService;
import com.example.springbootshiro.shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author carzy.
 * @date 15:02 2018/12/25
 */
@Component
public class PasswordRealm extends AuthorizingRealm {

    private UserService userService;
    private RoleService roleService;
    private PermissionService permissionService;
    private SysAdminProperties sysAdminProperties;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Autowired
    public void setSysAdminProperties(SysAdminProperties sysAdminProperties) {
        this.sysAdminProperties = sysAdminProperties;
    }

    /**
     * 限定只用 UsernamePasswordToken
     */
    @Override
    public Class<?> getAuthenticationTokenClass() {
        return UsernamePasswordToken.class;
    }

    /**
     * 这里只做认证,权限部分由 json web token 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String account = (String) principalCollection.getPrimaryPrincipal();
        List<Role> roles;
        List<Permission> permissions;
        if (sysAdminProperties.getName().equals(account)) {
            roles = this.roleService.findAll();
            permissions = this.permissionService.findAll();
        } else {
            roles = this.roleService.findAllByAccount(account);
            permissions = this.permissionService.findAllByRoleIds(roles.stream().map(Role::getId).collect(Collectors.toSet()));
        }
        authorizationInfo.setRoles(roles.stream().map(Role::getRoleName).collect(Collectors.toSet()));
        authorizationInfo.setStringPermissions(permissions.stream().map(Permission::getPermissionName).collect(Collectors.toSet()));
        return authorizationInfo;
    }

    /**
     * 登陆认证
     *
     * @param authenticationToken AuthenticationToken
     * @return 认证信息
     * @throws AuthenticationException 认证失败
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (!(authenticationToken instanceof UsernamePasswordToken)) {
            return null;
        }

        if (null == authenticationToken.getPrincipal() || null == authenticationToken.getCredentials()) {
            throw new UnknownAccountException();
        }
        String account = (String) authenticationToken.getPrincipal();
        if (sysAdminProperties.getName().equals(account)) {
            return new SimpleAuthenticationInfo(account, sysAdminProperties.getPassword(), getName());
        }
        try {
            UserInfo userInfo = this.userService.findByAccount(account);
            return new SimpleAuthenticationInfo(account, userInfo.getPassword(), getName());
        } catch (ResourceNotFoundException e) {
            return null;
        }
    }

}
