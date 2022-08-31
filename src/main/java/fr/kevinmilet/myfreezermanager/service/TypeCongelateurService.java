package fr.kevinmilet.myfreezermanager.service;

import fr.kevinmilet.myfreezermanager.entity.TypeCongelateur;

import java.util.List;

/**
 * @author k.milet
 */
public interface TypeCongelateurService {

    List<TypeCongelateur> getAllTypeCongelateur();

    TypeCongelateur createTypeCongelateur(TypeCongelateur typeCongelateur);

    TypeCongelateur updateTypeCongelateur(Long id, TypeCongelateur typeCongelateur) throws Exception;

    void deleteTypeCongelateur(Long Id) throws Exception;

    TypeCongelateur getTypeCongelateurById(Long id);
}
