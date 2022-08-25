package fr.kevinmilet.myfreezermanager.service;

import java.util.List;

import fr.kevinmilet.myfreezermanager.entity.Role;

public interface RoleService {

    List<Role> getAllRole();

    Role creatRole(Role role);

    Role updateRole(Long id, Role role) throws Exception;

    void deleteRole(Long id) throws Exception;

    Role getRoleById(Long id);
}
