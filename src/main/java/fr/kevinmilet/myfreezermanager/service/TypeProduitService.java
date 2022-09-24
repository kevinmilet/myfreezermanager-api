package fr.kevinmilet.myfreezermanager.service;

import fr.kevinmilet.myfreezermanager.dto.TypeProduitDto;

import java.util.List;

/**
 * @author k.milet
 */
public interface TypeProduitService {

    List<TypeProduitDto> getAllTypeProduit();

    TypeProduitDto createTypeProduit(TypeProduitDto typeProduitDto);

    TypeProduitDto updateTypeProduit(String id, TypeProduitDto typeProduitDto);

    TypeProduitDto getTypeProduitById(Long id);

    void deleteTypeProduit(Long Id);
}
