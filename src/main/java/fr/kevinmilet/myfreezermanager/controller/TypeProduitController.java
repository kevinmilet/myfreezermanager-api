package fr.kevinmilet.myfreezermanager.controller;

import fr.kevinmilet.myfreezermanager.dto.TypeCongelateurDto;
import fr.kevinmilet.myfreezermanager.dto.TypeProduitDto;
import fr.kevinmilet.myfreezermanager.entity.TypeCongelateur;
import fr.kevinmilet.myfreezermanager.entity.TypeProduit;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.kevinmilet.myfreezermanager.service.TypeProduitService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class TypeProduitController {

    private final TypeProduitService typeProduitService;
    private final ModelMapper modelMapper;

    @Autowired
    public TypeProduitController(TypeProduitService typeProduitService, ModelMapper modelMapper) {
        this.typeProduitService = typeProduitService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/types_produit")
    public List<TypeProduitDto> getAllTypeProduit() {
        return typeProduitService.getAllTypeProduit().stream()
                .map(typeProduit -> modelMapper.map(typeProduit, TypeProduitDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/types_produit/{id}")
    public ResponseEntity<TypeProduitDto> getTypeCongelateurById(@PathVariable(name = "id") Long id) {
        TypeProduit typeProduit = typeProduitService.getTypeProduitById(id);
        TypeProduitDto response = modelMapper.map(typeProduit, TypeProduitDto.class);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/types_produit/create")
    public ResponseEntity<TypeProduitDto> createTypeProduit(@RequestBody TypeProduitDto typeProduitDto) {

        // convert DTO to entity
        TypeProduit request = modelMapper.map(typeProduitDto, TypeProduit.class);

        TypeProduit typeProduit = typeProduitService.createTypeProduit(request);

        // convert entity to DTO
        TypeProduitDto response = modelMapper.map(typeProduit, TypeProduitDto.class);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/types_produit/update/{id}")
    public ResponseEntity<TypeProduitDto> updateTypePrtoduit(@PathVariable(name = "id") String id,
                                                                    @RequestBody TypeProduitDto typeProduitDto) throws Exception {

        // convert DTO to Entity
        TypeProduit request = modelMapper.map(typeProduitDto, TypeProduit.class);

        TypeProduit typeProduit = typeProduitService.updateTypeProduit(Long.parseLong(id), request);

        // entity to DTO
        TypeProduitDto response = modelMapper.map(typeProduit, TypeProduitDto.class);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/types_produit/delete/{id}")
    public ResponseEntity<String> suppressionTypeProduit(@PathVariable(name = "id") String id) throws Exception {
        typeProduitService.deleteTypeProduit(Long.parseLong(id));
        return new ResponseEntity<String>("Type de produit supprimé avec succes", HttpStatus.OK);
    }
}
