package com.example.springboot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.springboot.entity.Role;
import com.example.springboot.entity.User;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DBInit {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public DBInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @PostConstruct
    private void postConstruct() {
        Role role1 = new Role(1L,"ROLE_ADMIN");
        Role role2 = new Role(2L,"ROLE_USER");
        roleService.addRole(role1);
        roleService.addRole(role2);

        Set<Role> roles_admin = new HashSet<>();
        roles_admin.add(roleService.getRoleByName("ROLE_ADMIN"));
        User admin = new User(1L, "admin", "admin", "admin@admin.ru", "1234", roles_admin);
        userService.addUser(admin);

        Set<Role> roles_user = new HashSet<>();
        roles_user.add(roleService.getRoleByName("ROLE_USER"));
        User user = new User( 2L,"user", "user",
                "user@user.ru", "1234",  roles_user);
        userService.addUser(user);

    }
}
