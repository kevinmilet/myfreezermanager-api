package fr.kevinmilet.myfreezermanager.service;

import fr.kevinmilet.myfreezermanager.entity.Produit;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

public interface ProduitService {

    List<Produit> getAllProduits();

    List<Produit> getProduitUtilisateur(Principal principal);

    List<Produit> getProduitCongelateur(Long id);

    ResponseEntity<Produit> createProduit(Produit produit, Principal principal);

    Produit updateProduit(Long id, Produit produit) throws Exception;

    void deleteProduit(Long id) throws Exception;

    Produit getProduitById(Long id);
}
