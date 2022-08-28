package fr.kevinmilet.myfreezermanager.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
        String uuid = UUID.randomUUID().toString();

        utilisateur.setIdCompte(uuid);
        utilisateur.setDateCreation(Instant.now());

        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur updateUtilisateur(Long id, Utilisateur utilisateurRequest) throws Exception {
        Utilisateur utilisateur = utilisateurRepository.findById(id).orElseThrow(Exception::new);

        utilisateur.setNom(utilisateurRequest.getNom());
        utilisateur.setPrenom(utilisateurRequest.getPrenom());
        utilisateur.setEmail(utilisateurRequest.getEmail());
        utilisateur.setDateMaj(Instant.now());

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

//    public Utilisateur desactiverUtilisateur(Long id, Utilisateur utilisateurRequest) throws Exception {
//	Utilisateur utilisateur = utilisateurRepository.findById(id).orElseThrow(() -> new Exception());
//	
//	utilisateur.setNom(utilisateurRequest.getNom());
//    }

}
