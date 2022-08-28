package fr.kevinmilet.myfreezermanager.controller;

import fr.kevinmilet.myfreezermanager.dto.CongelateurDto;
import fr.kevinmilet.myfreezermanager.dto.UtilisateurDto;
import fr.kevinmilet.myfreezermanager.entity.Congelateur;
import fr.kevinmilet.myfreezermanager.entity.Utilisateur;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
