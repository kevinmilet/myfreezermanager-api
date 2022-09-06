package fr.kevinmilet.myfreezermanager.service;

import java.util.List;

import fr.kevinmilet.myfreezermanager.entity.Utilisateur;
import org.springframework.http.ResponseEntity;

public interface UtilisateurService {

    List<Utilisateur> getAllUtilisateurs();

    Utilisateur createUtilisateur(Utilisateur utilisateur);

    Utilisateur updateUtilisateur(Long id, Utilisateur utilisateur) throws Exception;

    void deleteUtilisateur(Long id) throws Exception;

    Utilisateur getUtilisateurById(Long id);

    Utilisateur findUtilisateurByEmail(String email);

    Utilisateur activerUtilisateur(Long id, Utilisateur utilisateurRequest) throws Exception;

    Utilisateur setUtilisateurAdmin(Long id, Utilisateur utilisateurRequest) throws Exception;
}
