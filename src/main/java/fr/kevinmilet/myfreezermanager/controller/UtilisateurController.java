package fr.kevinmilet.myfreezermanager.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.kevinmilet.myfreezermanager.dto.UtilisateurDto;
import fr.kevinmilet.myfreezermanager.entity.Utilisateur;
import fr.kevinmilet.myfreezermanager.service.UtilisateurService;

@RestController
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private final ModelMapper modelMapper;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService, ModelMapper modelMapper) {
        this.utilisateurService = utilisateurService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/utilisateurs")
    public List<UtilisateurDto> getAllUtilisateurs() {

	return utilisateurService.getAllUtilisateurs().stream().map(utilisateur -> modelMapper.map(utilisateur, UtilisateurDto.class))
		.collect(Collectors.toList());
    }

    @GetMapping("/utilisateur/{id}")
    public ResponseEntity<UtilisateurDto> getUtilisateurById(@PathVariable(name = "id") Long id) {
        Utilisateur utilisateur = utilisateurService.getUtilisateurById(id);
        UtilisateurDto response = modelMapper.map(utilisateur, UtilisateurDto.class);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/utilisateur/create")
    public ResponseEntity<UtilisateurDto> createUtilisateur(@RequestBody UtilisateurDto utilisateurDto) {

        Utilisateur utilisateurExiste = utilisateurService.findUtilisateurByEmail(utilisateurDto.getEmail());

        if (utilisateurExiste != null) {
            return new ResponseEntity("User already existing", HttpStatus.BAD_REQUEST);
        }
        // convert DTO to entity
        Utilisateur request = modelMapper.map(utilisateurDto, Utilisateur.class);

        Utilisateur utilisateur = utilisateurService.createUtilisateur(request);

        // convert entity to DTO
        UtilisateurDto response = modelMapper.map(utilisateur, UtilisateurDto.class);

        return new ResponseEntity<UtilisateurDto>(response, HttpStatus.CREATED);
    }

    @PutMapping("/utilisateur/update/{id}")
    public ResponseEntity<UtilisateurDto> updateUtilisateur(@PathVariable long id,
	    @RequestBody UtilisateurDto utilisateurDto) throws Exception {

        // convert DTO to Entity
        Utilisateur request = modelMapper.map(utilisateurDto, Utilisateur.class);

        Utilisateur utilisateur = utilisateurService.updateUtilisateur(id, request);

        // entity to DTO
        UtilisateurDto response = modelMapper.map(utilisateur, UtilisateurDto.class);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/utilisateur/delete/{id}")
    public ResponseEntity<String> suppressionPhysiqueUtilisateur(@PathVariable(name = "id") Long id) throws Exception {
        utilisateurService.deleteUtilisateur(id);
        return new ResponseEntity<String>("Utilisateur supprimé avec succes", HttpStatus.OK);
    }

//    @DeleteMapping("/utilisateur/soft_delete/{id}")
//    public ResponseEntity<String> suppressionLogiqueUtilisateur(@PathVariable(name = "id") Long id) throws Exception {
//	utilisateurService.deleteUtilisateur(id);
//	return new ResponseEntity<String>("Utilisateur supprimé avec succes", HttpStatus.OK);
//    }
}
