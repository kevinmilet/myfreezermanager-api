package fr.kevinmilet.myfreezermanager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.kevinmilet.myfreezermanager.entity.Utilisateur;
import fr.kevinmilet.myfreezermanager.repository.UtilisateurRepository;
import fr.kevinmilet.myfreezermanager.service.UtilisateurService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public List<Utilisateur> getAllUtilisateurs() {
	return utilisateurRepository.findAll();
    }

    @Override
    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
	return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur updateUtilisateur(Long id, Utilisateur utilisateurRequest) throws Exception {
	Utilisateur utilisateur = utilisateurRepository.findById(id).orElseThrow(() -> new Exception());

	utilisateur.setNom(utilisateurRequest.getNom());
	utilisateur.setPrenom(utilisateurRequest.getPrenom());
	utilisateur.setEmail(utilisateurRequest.getEmail());

	return utilisateurRepository.save(utilisateur);
    }

    @Override
    public void deleteUtilisateur(Long id) throws Exception {
	Utilisateur utilisateur = utilisateurRepository.findById(id).orElseThrow(() -> new Exception());

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

//    public Utilisateur desactiverUtilisateur(Long id, Utilisateur utilisateurRequest) throws Exception {
//	Utilisateur utilisateur = utilisateurRepository.findById(id).orElseThrow(() -> new Exception());
//	
//	utilisateur.setNom(utilisateurRequest.getNom());
//    }

}
