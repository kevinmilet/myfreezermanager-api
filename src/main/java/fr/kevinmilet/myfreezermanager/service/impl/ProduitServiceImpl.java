package fr.kevinmilet.myfreezermanager.service.impl;

import fr.kevinmilet.myfreezermanager.controller.AdminController;
import fr.kevinmilet.myfreezermanager.entity.Produit;
import fr.kevinmilet.myfreezermanager.entity.TypeCongelateur;
import fr.kevinmilet.myfreezermanager.entity.TypeProduit;
import fr.kevinmilet.myfreezermanager.entity.Utilisateur;
import fr.kevinmilet.myfreezermanager.repository.*;
import fr.kevinmilet.myfreezermanager.service.ProduitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;
    private final AdminController adminController;
    private final UtilisateurRepository utilisateurRepository;
    private final TypeProduitRepository typeProduitRepository;

    @Autowired
    public ProduitServiceImpl(ProduitRepository produitRepository, AdminController adminController,
                              UtilisateurRepository utilisateurRepository, TypeProduitRepository typeProduitRepository) {
        this.produitRepository = produitRepository;
        this.adminController = adminController;
        this.utilisateurRepository = utilisateurRepository;
        this.typeProduitRepository = typeProduitRepository;
    }

    @Override
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    @Override
    public List<Produit> getProduitUtilisateur(Principal principal) {
        Long utilisateurConnecteId = adminController.getUserConnectedId(principal);
        return produitRepository.findProduitByUtilisateurId(utilisateurConnecteId);
    }

    @Override
    public List<Produit> getProduitCongelateur(Long id) {

        if (id != null) {
            return produitRepository.findProduitByCongelateurId(id);
        }
        return null;
    }

    @Override
    public ResponseEntity<Produit> createProduit(Produit produit, Principal principal) {
        Long utilisateurConnecteId = adminController.getUserConnectedId(principal);
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(utilisateurConnecteId);

        Optional<TypeProduit> typeProduit = typeProduitRepository.findById(produit.getTypeProduit().getId());

        if (typeProduit.isPresent()) {
            produit.setTypeProduit(typeProduit.get());
        } else {
            return new ResponseEntity("Vous devez indiquer le type de produit", HttpStatus.BAD_REQUEST);
        }

        if (utilisateur.isPresent()) {
            produit.setUtilisateur(utilisateur.get());
        } else {
            return new ResponseEntity("L'utilisateur n'a pas été trouvé", HttpStatus.BAD_REQUEST);
        }

        String uuid = UUID.randomUUID().toString().replace("-", "");

        produit.setDateAjout(LocalDate.now());
        produit.setDateCreation(Instant.now());
        produit.setIdProduit(uuid);

        produitRepository.save(produit);

        return new ResponseEntity(produit, HttpStatus.CREATED);
    }

    @Override
    public Produit updateProduit(Long id, Produit produit) throws Exception {
        return null;
    }

    @Override
    public void deleteProduit(Long id) throws Exception {

    }

    @Override
    public Produit getProduitById(Long id) {
        return null;
    }
}
