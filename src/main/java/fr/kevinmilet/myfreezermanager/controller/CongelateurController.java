package fr.kevinmilet.myfreezermanager.controller;

import fr.kevinmilet.myfreezermanager.dto.CongelateurDto;
import fr.kevinmilet.myfreezermanager.dto.UtilisateurDto;
import fr.kevinmilet.myfreezermanager.entity.Congelateur;
import fr.kevinmilet.myfreezermanager.entity.Utilisateur;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.kevinmilet.myfreezermanager.service.CongelateurService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CongelateurController {

    private final CongelateurService congelateurService;
    private final ModelMapper modelMapper;

    @Autowired
    public CongelateurController (CongelateurService congelateurService, ModelMapper modelMapper) {
        this.congelateurService = congelateurService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/congelateurs")
    public List<CongelateurDto> getAllCongelateur() {

        return congelateurService.getAllCongelateur().stream().map(congelateur -> modelMapper.map(congelateur, CongelateurDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/congelateur/{id}")
    public ResponseEntity<CongelateurDto> getCongelateurById(@PathVariable(name = "id") Long id) {
        Congelateur congelateur = congelateurService.getCongelateurById(id);
        CongelateurDto response = modelMapper.map(congelateur, CongelateurDto.class);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/congelateur/create")
    public ResponseEntity<CongelateurDto> createCongelateur(@RequestBody CongelateurDto congelateurDto) {

        // convert DTO to entity
        Congelateur request = modelMapper.map(congelateurDto, Congelateur.class);

        Congelateur congelateur = congelateurService.createCongelateur(request);

        // convert entity to DTO
        CongelateurDto response = modelMapper.map(congelateur, CongelateurDto.class);

        return new ResponseEntity<CongelateurDto>(response, HttpStatus.CREATED);
    }

    @PutMapping("/congelateur/update/{id}")
    public ResponseEntity<CongelateurDto> updateCongelateur(@PathVariable long id,
                                                            @RequestBody CongelateurDto congelateurDto) throws Exception {

        // convert DTO to Entity
        Congelateur request = modelMapper.map(congelateurDto, Congelateur.class);

        Congelateur congelateur = congelateurService.updateCongelateur(id, request);

        // entity to DTO
        CongelateurDto response = modelMapper.map(congelateur, CongelateurDto.class);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/congelateur/delete/{id}")
    public ResponseEntity<String> suppressionCongelateur(@PathVariable(name = "id") Long id) throws Exception {
        congelateurService.deleteCongelateur(id);
        return new ResponseEntity<String>("Congélateur supprimé avec succes", HttpStatus.OK);
    }

    @GetMapping("/utilsateur/congelateur/")
    public List<CongelateurDto> getCongelateurPerUtilisateur(Utilisateur utilisateur) {
        return congelateurService.getCongelateurByUtilisateur(utilisateur)
                .stream()
                .map(congelateur -> modelMapper.map(congelateur, CongelateurDto.class))
                .collect(Collectors.toList());
    }
}
