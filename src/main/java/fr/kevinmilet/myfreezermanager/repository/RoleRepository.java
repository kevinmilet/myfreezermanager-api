package fr.kevinmilet.myfreezermanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.kevinmilet.myfreezermanager.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
