package com.sda.component;

import com.sda.model.Role;
import com.sda.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

import static com.sda.utils.Constants.Security.*;


@Component
public class DataInit {
    @Autowired
    private RoleService roleService;

    @PostConstruct
    public void init() {
        initRoleData();
    }

    private void initRoleData() {
        System.out.println("Starting roles init..");

        Role roleAdmin = new Role();
        roleAdmin.setName(SECURITY_ROLE_ADMIN);
        createRole(roleAdmin);

        //Do for other roles

        Role roleUser = new Role();
        roleUser.setName(SECURITY_ROLE_USER);
        createRole(roleUser);

        Role roleCreator = new Role();
        roleCreator.setName(SECURITY_ROLE_CREATOR);
        createRole(roleCreator);


    }


    private void createRole(Role role){
        try{
            Role resultRole = roleService.findRoleByName(role.getName());
            System.out.println("Role(name:" + role.getName() + ")cannot be initialized!");
        } catch (Exception e) {
            roleService.createRole(role);
        }
    }
 }
