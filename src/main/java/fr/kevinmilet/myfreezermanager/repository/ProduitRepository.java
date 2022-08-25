package fr.kevinmilet.myfreezermanager.repository;

import org.springframework.data.repository.CrudRepository;

import fr.kevinmilet.myfreezermanager.entity.Produit;

public interface ProduitRepository extends CrudRepository<Produit, Long> {

}
