package fr.kevinmilet.myfreezermanager.service;

import fr.kevinmilet.myfreezermanager.dto.TypeCongelateurDto;

import java.util.List;

/**
 * @author k.milet
 */
public interface TypeCongelateurService {

    List<TypeCongelateurDto> getAllTypeCongelateur();

    TypeCongelateurDto createTypeCongelateur(TypeCongelateurDto typeCongelateurDto);

    TypeCongelateurDto updateTypeCongelateur(Long id, TypeCongelateurDto typeCongelateurDto);

    void deleteTypeCongelateur(Long Id);

    TypeCongelateurDto getTypeCongelateurById(Long id);
}
