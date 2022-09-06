package fr.kevinmilet.myfreezermanager.repository;

import fr.kevinmilet.myfreezermanager.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import fr.kevinmilet.myfreezermanager.entity.Congelateur;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CongelateurRepository extends JpaRepository<Congelateur, Long> {

    List<Congelateur> findCongelateurByUtilisateurId(Long id);
}
