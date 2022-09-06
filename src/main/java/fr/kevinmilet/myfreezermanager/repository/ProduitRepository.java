package fr.kevinmilet.myfreezermanager.repository;

import fr.kevinmilet.myfreezermanager.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

    List<Produit> findProduitByUtilisateurId(Long id);

    List<Produit> findProduitByCongelateurId(Long id);
}
