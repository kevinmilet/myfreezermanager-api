package fr.kevinmilet.myfreezermanager.controller;

import fr.kevinmilet.myfreezermanager.dto.CongelateurDto;
import fr.kevinmilet.myfreezermanager.dto.TypeCongelateurDto;
import fr.kevinmilet.myfreezermanager.entity.Congelateur;
import fr.kevinmilet.myfreezermanager.entity.TypeCongelateur;
import fr.kevinmilet.myfreezermanager.service.TypeCongelateurService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author k.milet
 */
@RestController
public class TypeCongelateurController {

    private final TypeCongelateurService typeCongelateurService;
    private final ModelMapper modelMapper;

    @Autowired
    public TypeCongelateurController(TypeCongelateurService typeCongelateurService, ModelMapper modelMapper) {
        this.typeCongelateurService = typeCongelateurService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/types_congelateur")
    public List<TypeCongelateurDto> getAllTypeCongelateur() {
        return typeCongelateurService.getAllTypeCongelateur().stream()
                .map(typeCongelateur -> modelMapper.map(typeCongelateur, TypeCongelateurDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/type_congelateur/{id}")
    public ResponseEntity<TypeCongelateurDto> getTypeCongelateurById(@PathVariable(name = "id") Long id) {
        TypeCongelateur typeCongelateur = typeCongelateurService.getTypeCongelateurById(id);
        TypeCongelateurDto response = modelMapper.map(typeCongelateur, TypeCongelateurDto.class);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/type_congelateur/create")
    public ResponseEntity<TypeCongelateurDto> createTypeCongelateurCongelateur(@RequestBody TypeCongelateurDto typeCongelateurDto) {

        // convert DTO to entity
        TypeCongelateur request = modelMapper.map(typeCongelateurDto, TypeCongelateur.class);

        TypeCongelateur typeCongelateur = typeCongelateurService.createTypeCongelateur(request);

        // convert entity to DTO
        TypeCongelateurDto response = modelMapper.map(typeCongelateur, TypeCongelateurDto.class);

        return new ResponseEntity<TypeCongelateurDto>(response, HttpStatus.CREATED);
    }

    @PutMapping("/type_congelateur/update/{id}")
    public ResponseEntity<TypeCongelateurDto> updateTypeCongelateur(@PathVariable long id,
                                                            @RequestBody TypeCongelateurDto typeCongelateurDto) throws Exception {

        // convert DTO to Entity
        TypeCongelateur request = modelMapper.map(typeCongelateurDto, TypeCongelateur.class);

        TypeCongelateur typeCongelateur = typeCongelateurService.updateTypeCongelateur(id, request);

        // entity to DTO
        TypeCongelateurDto response = modelMapper.map(typeCongelateur, TypeCongelateurDto.class);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/type_congelateur/delete/{id}")
    public ResponseEntity<String> suppressionTypeCongelateur(@PathVariable(name = "id") Long id) throws Exception {
        typeCongelateurService.deleteTypeCongelateur(id);
        return new ResponseEntity<String>("Type de congélateur supprimé avec succes", HttpStatus.OK);
    }
}
