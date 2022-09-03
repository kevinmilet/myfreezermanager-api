package fr.kevinmilet.myfreezermanager.service;

import fr.kevinmilet.myfreezermanager.entity.TypeProduit;

import java.util.List;

/**
 * @author k.milet
 */
public interface TypeProduitService {

    List<TypeProduit> getAllTypeProduit();

    TypeProduit createTypeProduit(TypeProduit typeProduit);

    TypeProduit updateTypeProduit(Long id, TypeProduit typeProduit) throws Exception;

    void deleteTypeProduit(Long Id) throws Exception;

    TypeProduit getTypeProduitById(Long id);
}
