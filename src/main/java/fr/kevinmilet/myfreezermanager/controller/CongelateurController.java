package fr.kevinmilet.myfreezermanager.controller;

import fr.kevinmilet.myfreezermanager.dto.CongelateurDto;
import fr.kevinmilet.myfreezermanager.entity.Congelateur;
import fr.kevinmilet.myfreezermanager.service.CongelateurService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@SecurityRequirement(name = "bearerAuth")
@Slf4j
public class CongelateurController {

    private final CongelateurService congelateurService;

    @Autowired
    public CongelateurController (CongelateurService congelateurService) {
        this.congelateurService = congelateurService;
    }

    @GetMapping(value = "/congelateurs")
    public List<CongelateurDto> getAllCongelateur() {

        return congelateurService.getAllCongelateur().stream()
                .map(CongelateurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/mes_congelateurs")
    public List<CongelateurDto> getCongelateursUtilisateur(Principal principal) {

        return  congelateurService.getCongelateurUtilisateur(principal).stream()
                .map(CongelateurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/congelateur/{id}")
    public Congelateur getCongelateurById(@PathVariable(name = "id") String id) {
        if (id == null) {
            log.error("L'ID du congélateur est null");
            return null;
        }

        return congelateurService.getCongelateurById(Long.parseLong(id));
    }

    @PostMapping("/congelateur/create")
    public CongelateurDto createCongelateur(@RequestBody CongelateurDto congelateurDto, Principal principal) {

        return CongelateurDto.fromEntity(
                congelateurService.createCongelateur(CongelateurDto.toEntity(congelateurDto), principal).getBody()
        );
    }

    @PutMapping("/congelateur/update/{id}")
    public Congelateur updateCongelateur(@PathVariable(name = "id") String id,
                                         @RequestBody CongelateurDto congelateurDto) throws Exception {

        if (id == null) {
            log.error("L'ID du congélateur est null");
            return null;
        }

        if (congelateurDto == null) {
            log.error("Le congélateur est inconnu");
            return null;
        }

        return congelateurService.updateCongelateur(Long.parseLong(id), CongelateurDto.toEntity(congelateurDto));
    }

    @DeleteMapping("/congelateur/delete/{id}")
    public ResponseEntity<String> suppressionCongelateur(@PathVariable(name = "id") String id) throws Exception {
        congelateurService.deleteCongelateur(Long.parseLong(id));
        return new ResponseEntity<String>("Congélateur supprimé avec succes", HttpStatus.OK);
    }
}
