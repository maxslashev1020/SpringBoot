package com.example.springboot.service;

import com.example.springboot.entity.Role;


import java.util.List;

public interface RoleService {
    void addRole(Role role);

    void updateRole(Role role);

    void removeRoleById(long id);

    List<Role> getAllRoles();

    Role getRoleByName(String name);

}
