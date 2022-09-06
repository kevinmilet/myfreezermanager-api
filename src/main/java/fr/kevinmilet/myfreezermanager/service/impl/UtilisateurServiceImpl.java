package fr.kevinmilet.myfreezermanager.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fr.kevinmilet.myfreezermanager.entity.Utilisateur;
import fr.kevinmilet.myfreezermanager.repository.UtilisateurRepository;
import fr.kevinmilet.myfreezermanager.service.UtilisateurService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Utilisateur createUtilisateur(Utilisateur utilisateur) {

        String uuid = UUID.randomUUID().toString().replace("-", "");

        Utilisateur utilisateurToSave = new Utilisateur();

        utilisateurToSave.setEmail(utilisateur.getEmail());
        utilisateurToSave.setPassword((new BCryptPasswordEncoder().encode(utilisateur.getPassword())));
        utilisateurToSave.setNom(utilisateur.getNom());
        utilisateurToSave.setPrenom(utilisateur.getPrenom());
        utilisateurToSave.setIdCompte(uuid);
        utilisateurToSave.setCreated_at(Instant.now());
        utilisateurToSave.setPassword_request(Boolean.FALSE);
        utilisateurToSave.setToken(null);
        utilisateurToSave.setActive(Boolean.TRUE);
        utilisateurToSave.setAdmin(Boolean.FALSE);

        utilisateurRepository.save(utilisateurToSave);

        return utilisateurToSave;
    }

    @Override
    public Utilisateur updateUtilisateur(Long id, Utilisateur utilisateurRequest) throws Exception {
        Utilisateur utilisateur = utilisateurRepository.findById(id).orElseThrow(Exception::new);

        utilisateur.setNom(utilisateurRequest.getNom());
        utilisateur.setPrenom(utilisateurRequest.getPrenom());
        utilisateur.setEmail(utilisateurRequest.getEmail());
        utilisateur.setUpdated_at(Instant.now());

        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public void deleteUtilisateur(Long id) throws Exception {
        Utilisateur utilisateur = utilisateurRepository.findById(id).orElseThrow(Exception::new);

        utilisateurRepository.delete(utilisateur);
    }

    @Override
    public Utilisateur getUtilisateurById(Long id) {
        Optional<Utilisateur> result = utilisateurRepository.findById(id);

        if (result.isPresent()) {
            return result.get();
        }

        return null;
    }

    @Override
    public Utilisateur findUtilisateurByEmail(String email) {
        Optional<Utilisateur> result = Optional.ofNullable(utilisateurRepository.findOneByEmail(email));

        if (result.isPresent()) {
            return result.get();
        }

        return null;
    }

    @Override
    public Utilisateur activerUtilisateur(Long id, Utilisateur utilisateurRequest) throws Exception {
        Utilisateur utilisateur = utilisateurRepository.findById(id).orElseThrow(Exception::new);

        Boolean isActive = utilisateur.getActive();

        if (isActive.equals(Boolean.TRUE)) {
            utilisateur.setActive(Boolean.FALSE);
        } else {
            utilisateur.setActive(Boolean.TRUE);
        }

        utilisateur.setUpdated_at(Instant.now());

        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur setUtilisateurAdmin(Long id, Utilisateur utilisateurRequest) throws Exception {
        Utilisateur utilisateur = utilisateurRepository.findById(id).orElseThrow(Exception::new);

        Boolean isActive = utilisateur.getAdmin();

        if (isActive.equals(Boolean.FALSE)) {
            utilisateur.setActive(Boolean.TRUE);
        } else {
            utilisateur.setActive(Boolean.FALSE);
        }

        utilisateur.setUpdated_at(Instant.now());

        return utilisateurRepository.save(utilisateur);
    }

}
