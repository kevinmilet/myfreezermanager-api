package fr.kevinmilet.myfreezermanager.service;

import fr.kevinmilet.myfreezermanager.entity.Congelateur;
import fr.kevinmilet.myfreezermanager.entity.Utilisateur;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

public interface CongelateurService {

    List<Congelateur> getAllCongelateur();

    ResponseEntity createCongelateur(Congelateur congelateur, Principal principal);

    Congelateur updateCongelateur(Long id, Congelateur congelateur) throws Exception;

    void deleteCongelateur(Long id) throws Exception;

    Congelateur getCongelateurById(Long id);

}
