package fr.kevinmilet.myfreezermanager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.kevinmilet.myfreezermanager.entity.Role;
import fr.kevinmilet.myfreezermanager.repository.RoleRepository;
import fr.kevinmilet.myfreezermanager.service.RoleService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getAllRole() {
	return roleRepository.findAll();
    }

    @Override
    public Role creatRole(Role role) {
	return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Long id, Role roleRequest) throws Exception {
        Role role = roleRepository.findById(id).orElseThrow(() -> new Exception());

        role.setRole(roleRequest.getRole());
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long id) throws Exception {
        Role role = roleRepository.findById(id).orElseThrow(() -> new Exception());
        roleRepository.delete(role);
    }

    @Override
    public Role getRoleById(Long id) {
        Optional<Role> result = roleRepository.findById(id);

        if (result.isPresent()) {
            return result.get();
        }

        return null;
    }

}
