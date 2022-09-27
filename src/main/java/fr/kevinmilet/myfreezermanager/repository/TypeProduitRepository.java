package fr.kevinmilet.myfreezermanager.repository;

import fr.kevinmilet.myfreezermanager.dto.TypeProduitDto;
import fr.kevinmilet.myfreezermanager.entity.TypeProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author k.milet
 */
public interface TypeProduitRepository extends JpaRepository<TypeProduit, Long> {

    @Transactional
    @Modifying
    @Query("update TypeProduit tc set tc.nom = :nom where tc.id = :id")
    void updateTypeProduit(@Param(value = "id") Long id, @Param(value = "nom") String nom);
}
