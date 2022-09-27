package fr.kevinmilet.myfreezermanager.repository;

import fr.kevinmilet.myfreezermanager.entity.TypeCongelateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author k.milet
 */
public interface TypeCongelateurRepository extends JpaRepository<TypeCongelateur, Long> {

    @Transactional
    @Modifying
    @Query("update TypeCongelateur tc set tc.nom = :nom where tc.id = :id")
    void updateTypeCongelateur(@Param(value = "id") Long id, @Param(value = "nom") String nom);
}
