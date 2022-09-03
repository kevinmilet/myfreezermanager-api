package fr.kevinmilet.myfreezermanager.repository;

import fr.kevinmilet.myfreezermanager.entity.TypeCongelateur;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author k.milet
 */
public interface TypeCongelateurRepository extends JpaRepository<TypeCongelateur, Long> {
}
