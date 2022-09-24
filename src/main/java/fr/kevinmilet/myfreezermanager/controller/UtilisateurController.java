package fr.kevinmilet.myfreezermanager.controller;

import fr.kevinmilet.myfreezermanager.dto.UtilisateurDto;
import fr.kevinmilet.myfreezermanager.entity.Utilisateur;
import fr.kevinmilet.myfreezermanager.jwt.JwtController;
import fr.kevinmilet.myfreezermanager.jwt.JwtFilter;
import fr.kevinmilet.myfreezermanager.jwt.JwtUtils;
import fr.kevinmilet.myfreezermanager.service.UtilisateurService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private final ModelMapper modelMapper;
    private final JwtController jwtController;
    private final JwtUtils jwtUtils;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService, ModelMapper modelMapper,
                                 JwtController jwtController, JwtUtils jwtUtils) {
        this.utilisateurService = utilisateurService;
        this.modelMapper = modelMapper;
        this.jwtController = jwtController;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/creer_utilisateur")
    public ResponseEntity createUtilisateur(@Valid @RequestBody UtilisateurDto utilisateurDto) {

        Utilisateur utilisateurExiste = utilisateurService.findUtilisateurByEmail(utilisateurDto.getEmail());

        if (utilisateurExiste != null) {
            return new ResponseEntity("User already existing", HttpStatus.BAD_REQUEST);
        }

        // convert DTO to entity
        Utilisateur request = modelMapper.map(utilisateurDto, Utilisateur.class);

        Utilisateur utilisateur = utilisateurService.createUtilisateur(request);

        // convert entity to DTO
        UtilisateurDto response = modelMapper.map(utilisateur, UtilisateurDto.class);

        Authentication authentication = jwtController.logUser(utilisateurDto.getEmail(), utilisateurDto.getPassword());
        String jwt = jwtUtils.generateToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(response, httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping(value = "/isConnected")
    public ResponseEntity getUSerConnected() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return new ResponseEntity(((UserDetails) principal).getUsername(), HttpStatus.OK);
        }
        return new ResponseEntity("User is not connected", HttpStatus.FORBIDDEN);
    }
}
