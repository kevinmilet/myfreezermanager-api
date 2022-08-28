package fr.kevinmilet.myfreezermanager.service;

import fr.kevinmilet.myfreezermanager.entity.Congelateur;
import fr.kevinmilet.myfreezermanager.entity.Utilisateur;

import java.util.List;

public interface CongelateurService {

    List<Congelateur> getAllCongelateur();

    Congelateur createCongelateur(Congelateur congelateur);

    Congelateur updateCongelateur(Long id, Congelateur congelateur) throws Exception;

    void deleteCongelateur(Long id) throws Exception;

    Congelateur getCongelateurById(Long id);

    List<Congelateur> getCongelateurByUtilisateur(Utilisateur utilisateur);
}
