package com.sda.service;

import com.sda.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role findRoleByName(String name) throws Exception;

    void createRole(Role role);

    void updateRole(Role role) throws Exception;

    List<Role> findAllRoles();

    void deleteRoleById(Long id) throws Exception;

    void restoreRoleById(Long id) throws Exception;

    Role findRoleById(Long id) throws Exception;
}
