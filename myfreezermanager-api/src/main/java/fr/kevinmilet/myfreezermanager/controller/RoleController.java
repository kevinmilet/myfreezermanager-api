package fr.kevinmilet.myfreezermanager.controller;

import java.util.List;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.kevinmilet.myfreezermanager.dto.RoleDto;
import fr.kevinmilet.myfreezermanager.entity.Role;
import fr.kevinmilet.myfreezermanager.service.RoleService;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class RoleController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    RoleService roleService;

    @GetMapping(value = "/roles")
    public List<RoleDto> getAllRole() {
	return roleService.getAllRole().stream().map(role -> modelMapper.map(role, RoleDto.class))
		.collect(Collectors.toList());
    }

    @GetMapping(value = "/role/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable(name = "id") Long Id) {
	Role role = roleService.getRoleById(Id);
	// Convert entity to DTO
	RoleDto roleResponse = modelMapper.map(role, RoleDto.class);

	return ResponseEntity.ok().body(roleResponse);
    }

//    @PostMapping
//    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto) {
//	// convert DTO to entity
//	Role roleRequest = modelMapper.map(roleDto, Role.class);
//	Role role = roleService.creatRole(roleRequest);
//
//	// convert entity to DTO
//	RoleDto roleResponse = modelMapper.map(role, RoleDto.class);
//
//	return new ResponseEntity<RoleDto>(roleResponse, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/role/{id}")
//    public ResponseEntity<RoleDto> updatePost(@PathVariable Long id, @RequestBody RoleDto roleDto) throws Exception {
//
//	// convert DTO to Entity
//	Role roleRequest = modelMapper.map(roleDto, Role.class);
//
//	Role role = roleService.updateRole(id, roleRequest);
//
//	// entity to DTO
//	RoleDto roleResponse = modelMapper.map(role, RoleDto.class);
//
//	return ResponseEntity.ok().body(roleResponse);
//    }
//
//    @DeleteMapping("/role/{id}")
//    public ResponseEntity<String> deletePost(@PathVariable(name = "id") Long id) throws Exception {
//	roleService.deleteRole(id);
//	return new ResponseEntity<String>("Role supprimé avec succes", HttpStatus.OK);
//    }
}
