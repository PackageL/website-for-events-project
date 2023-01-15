package com.sda.service.impl;

import com.sda.model.Role;
import com.sda.repository.RoleRepository;
import com.sda.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findRoleByName(String name) throws Exception {
        Optional<Role> optionalRole = roleRepository.findByName(name);

        if(optionalRole.isPresent()) {
            return optionalRole.get();
        } else
            throw new Exception("Role(name:" + name + ") not found!");
    }

    @Override
    public void createRole(Role role) {
        role.setActive(true);
        roleRepository.save(role);
    }

    @Override
    public void updateRole(Role role) throws Exception {
        findRoleById(role.getId());
        roleRepository.saveAndFlush(role);

    }
    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteRoleById(Long id) throws Exception {
        Role role = findRoleById(id);
        role.setActive(false);
        roleRepository.save(role);
    }

    @Override
    public void restoreRoleById(Long id) throws Exception {
        Role role = findRoleById(id);
        role.setActive(true);
        roleRepository.save(role);
    }

    @Override
    public Role findRoleById(Long id) throws Exception {
        Optional<Role> optionalRole = roleRepository.findById(id);

        if(optionalRole.isPresent()) {
            return optionalRole.get();
        } else
            throw new Exception("Role(id:" + id + ") not found!");
    }
}
