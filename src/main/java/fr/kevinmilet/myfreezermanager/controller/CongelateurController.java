package fr.kevinmilet.myfreezermanager.controller;

import fr.kevinmilet.myfreezermanager.dto.CongelateurDto;
import fr.kevinmilet.myfreezermanager.entity.Congelateur;
import fr.kevinmilet.myfreezermanager.service.CongelateurService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@SecurityRequirement(name = "bearerAuth")
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

    @GetMapping("/mes_congelateurs")
    public List<CongelateurDto> getCongelateursUtilisateur(Principal principal) {
        return  congelateurService.getCongelateurUtilisateur(principal).stream().map(congelateur -> modelMapper.map(congelateur, CongelateurDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/congelateur/{id}")
    public ResponseEntity<CongelateurDto> getCongelateurById(@PathVariable(name = "id") String id) {
        Congelateur congelateur = congelateurService.getCongelateurById(Long.parseLong(id));
        CongelateurDto response = modelMapper.map(congelateur, CongelateurDto.class);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/congelateur/create")
    public ResponseEntity<CongelateurDto> createCongelateur(@RequestBody CongelateurDto congelateurDto, Principal principal) {

        // convert DTO to entity
        Congelateur request = modelMapper.map(congelateurDto, Congelateur.class);

        ResponseEntity congelateur = congelateurService.createCongelateur(request, principal);

        // convert entity to DTO
        CongelateurDto response = modelMapper.map(congelateur, CongelateurDto.class);

        return new ResponseEntity<CongelateurDto>(response, HttpStatus.CREATED);
    }

    @PutMapping("/congelateur/update/{id}")
    public ResponseEntity<CongelateurDto> updateCongelateur(@PathVariable(name = "id") String id,
                                                            @RequestBody CongelateurDto congelateurDto) throws Exception {

        // convert DTO to Entity
        Congelateur request = modelMapper.map(congelateurDto, Congelateur.class);

        Congelateur congelateur = congelateurService.updateCongelateur(Long.parseLong(id), request);

        // entity to DTO
        CongelateurDto response = modelMapper.map(congelateur, CongelateurDto.class);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/congelateur/delete/{id}")
    public ResponseEntity<String> suppressionCongelateur(@PathVariable(name = "id") String id) throws Exception {
        congelateurService.deleteCongelateur(Long.parseLong(id));
        return new ResponseEntity<String>("Congélateur supprimé avec succes", HttpStatus.OK);
    }
}
