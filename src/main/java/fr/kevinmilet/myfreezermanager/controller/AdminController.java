package fr.kevinmilet.myfreezermanager.controller;

import fr.kevinmilet.myfreezermanager.dto.UtilisateurDto;
import fr.kevinmilet.myfreezermanager.entity.Utilisateur;
import fr.kevinmilet.myfreezermanager.jwt.JwtController;
import fr.kevinmilet.myfreezermanager.jwt.JwtUtils;
import fr.kevinmilet.myfreezermanager.service.UtilisateurService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author k.milet
 */
@RestController
@SecurityRequirement(name = "bearerAuth")
public class AdminController {

    private final UtilisateurService utilisateurService;
    private final ModelMapper modelMapper;

    @Autowired
    public AdminController(UtilisateurService utilisateurService, ModelMapper modelMapper) {
        this.utilisateurService = utilisateurService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/isConnected")
    public ResponseEntity getUSerConnected() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return new ResponseEntity(((UserDetails) principal).getUsername(), HttpStatus.OK);
        }
        return new ResponseEntity("User is not connected", HttpStatus.FORBIDDEN);
    }

    public Long getUserConnectedId(Principal principal) {
        if (!(principal instanceof UsernamePasswordAuthenticationToken)) {
            throw new RuntimeException("User not found");
        }

        UsernamePasswordAuthenticationToken user = (UsernamePasswordAuthenticationToken) principal;
        Utilisateur oneByEmail = utilisateurService.findUtilisateurByEmail(user.getName());

        return oneByEmail.getId();
    }

    @GetMapping(value = "/utilisateurs")
    public List<UtilisateurDto> getAllUtilisateurs() {

        return utilisateurService.getAllUtilisateurs().stream()
                .map(utilisateur -> modelMapper.map(utilisateur, UtilisateurDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/utilisateur/{id}")
    public ResponseEntity<UtilisateurDto> getUtilisateurById(@PathVariable(name = "id") Long id) {
        Utilisateur utilisateur = utilisateurService.getUtilisateurById(id);
        UtilisateurDto response = modelMapper.map(utilisateur, UtilisateurDto.class);

        return ResponseEntity.ok().body(response);
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

    @PutMapping("/utilisateur/activation/{id}")
    public ResponseEntity<UtilisateurDto> desactiveUtilisateur(@PathVariable(name = "id") Long id, @RequestBody UtilisateurDto utilisateurDto) throws Exception {
        // convert DTO to Entity
        Utilisateur request = modelMapper.map(utilisateurDto, Utilisateur.class);

        Utilisateur utilisateur = utilisateurService.activerUtilisateur(id, request);

        // entity to DTO
        UtilisateurDto response = modelMapper.map(utilisateur, UtilisateurDto.class);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/utilisateur/set_admin/{id}")
    public ResponseEntity<UtilisateurDto> utilisateurRoleAdmin(@PathVariable(name = "id") Long id, @RequestBody UtilisateurDto utilisateurDto) throws Exception {
        // convert DTO to Entity
        Utilisateur request = modelMapper.map(utilisateurDto, Utilisateur.class);

        Utilisateur utilisateur = utilisateurService.setUtilisateurAdmin(id, request);

        // entity to DTO
        UtilisateurDto response = modelMapper.map(utilisateur, UtilisateurDto.class);

        return ResponseEntity.ok().body(response);
    }
}
