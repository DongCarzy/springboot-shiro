package com.example.springbootshiro.shiro.domain;

import javax.persistence.*;

/**
 * @author carzy.
 * @date 16:13 2018/12/24
 */
@Entity
@Table(name = "sys_role_permission")
public class SysRolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "sys_permission_id")
    private Permission permission;

    @ManyToOne
    @JoinColumn(name = "sys_role_id")
    private Role role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
