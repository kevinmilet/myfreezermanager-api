package fr.kevinmilet.myfreezermanager.service.impl;

import fr.kevinmilet.myfreezermanager.controller.AdminController;
import fr.kevinmilet.myfreezermanager.entity.Congelateur;
import fr.kevinmilet.myfreezermanager.entity.TypeCongelateur;
import fr.kevinmilet.myfreezermanager.entity.Utilisateur;
import fr.kevinmilet.myfreezermanager.repository.CongelateurRepository;
import fr.kevinmilet.myfreezermanager.repository.TypeCongelateurRepository;
import fr.kevinmilet.myfreezermanager.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fr.kevinmilet.myfreezermanager.service.CongelateurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.security.Principal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class CongelateurServiceImpl implements CongelateurService {

    private final CongelateurRepository congelateurRepository;
    private final AdminController adminController;
    private final UtilisateurRepository utilisateurRepository;
    private final TypeCongelateurRepository typeCongelateurRepository;

    @Autowired
    public CongelateurServiceImpl(CongelateurRepository congelateurRepository, AdminController adminController, UtilisateurRepository utilisateurRepository, TypeCongelateurRepository typeCongelateurRepository) {
        this.congelateurRepository = congelateurRepository;
        this.adminController = adminController;
        this.utilisateurRepository = utilisateurRepository;
        this.typeCongelateurRepository = typeCongelateurRepository;
    }

    @Override
    public List<Congelateur> getAllCongelateur() {
        return congelateurRepository.findAll();
    }

    @Override
    public List<Congelateur> getCongelateurUtilisateur(Principal principal) {
        Long utilisateurConnecteId = adminController.getUserConnectedId(principal);

        return congelateurRepository.findCongelateurByUtilisateurId(utilisateurConnecteId);
    }

    @Override
    public ResponseEntity createCongelateur(@Valid @RequestBody Congelateur congelateur, Principal principal) {
        Long utilisateurConnecteId = adminController.getUserConnectedId(principal);
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(utilisateurConnecteId);

        Optional<TypeCongelateur> typeCongelateur = typeCongelateurRepository.findById(congelateur.getTypeCongelateur().getId());

        String uuid = UUID.randomUUID().toString().replace("-", "");

        if (typeCongelateur.isPresent()) {
            congelateur.setTypeCongelateur(typeCongelateur.get());
        } else {
            return new ResponseEntity("Vous devez indiquer le type de congélateur", HttpStatus.BAD_REQUEST);
        }

        if (utilisateur.isPresent()) {
            congelateur.setUtilisateur(utilisateur.get());
        } else {
            return new ResponseEntity("L'utilisateur n'a pas été trouvé", HttpStatus.BAD_REQUEST);
        }

        congelateur.setIdCongelateur(uuid);
        congelateur.setDateCreation(Instant.now());

        congelateurRepository.save(congelateur);

        return new ResponseEntity(congelateur, HttpStatus.CREATED);
    }

    @Override
    public Congelateur updateCongelateur(Long id, Congelateur congelateurRequest) throws Exception {
        Congelateur congelateur = congelateurRepository.findById(id).orElseThrow(Exception::new);

        congelateur.setNom(congelateurRequest.getNom());
        congelateur.setTypeCongelateur(congelateurRequest.getTypeCongelateur());
        congelateur.setDateMaj(Instant.now());

        return congelateurRepository.save(congelateur);
    }

    @Override
    public void deleteCongelateur(Long id) throws Exception {
        Congelateur congelateur = congelateurRepository.findById(id).orElseThrow(Exception::new);

        congelateurRepository.delete(congelateur);
    }

    @Override
    public Congelateur getCongelateurById(Long id) {
        Optional<Congelateur> result = congelateurRepository.findById(id);

        if (result.isPresent()) {
            return result.get();
        }

        return null;
    }

}
