package fr.kevinmilet.myfreezermanager.service.impl;

import fr.kevinmilet.myfreezermanager.dto.TypeCongelateurDto;
import fr.kevinmilet.myfreezermanager.entity.TypeCongelateur;
import fr.kevinmilet.myfreezermanager.repository.TypeCongelateurRepository;
import fr.kevinmilet.myfreezermanager.service.TypeCongelateurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author k.milet
 */
@Service
@Slf4j
public class TypeCongelateurServiceImpl implements TypeCongelateurService {

    private final TypeCongelateurRepository typeCongelateurRepository;

    @Autowired
    public TypeCongelateurServiceImpl(TypeCongelateurRepository typeCongelateurRepository) {
        this.typeCongelateurRepository = typeCongelateurRepository;
    }

    @Override
    public List<TypeCongelateurDto> getAllTypeCongelateur() {

        return typeCongelateurRepository.findAll().stream()
                .map(TypeCongelateurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TypeCongelateurDto createTypeCongelateur(TypeCongelateurDto typeCongelateurDto) {
        return TypeCongelateurDto.fromEntity(
                typeCongelateurRepository.save(Objects.requireNonNull(TypeCongelateurDto.toEntity(typeCongelateurDto)))
        );
    }

    @Override
    public TypeCongelateurDto updateTypeCongelateur(Long id, TypeCongelateurDto typeCongelateurDto) {
            typeCongelateurRepository.updateTypeCongelateur(id, typeCongelateurDto.getNom());
            return typeCongelateurDto;
    }

    @Override
    public void deleteTypeCongelateur(Long id) {
        if (id == null) {
            log.error("Type congelateur Id is null");
            return;
        }

        Optional<TypeCongelateur> typeCongelateur = typeCongelateurRepository.findById(id);
        typeCongelateur.ifPresent(typeCongelateurRepository::delete);

    }

    @Override
    public TypeCongelateurDto getTypeCongelateurById(Long id) {
        if (id == null) {
            log.error("Type congelateur Id is null");
            return null;
        }

        Optional<TypeCongelateur> result = typeCongelateurRepository.findById(id);

        if (result.map(TypeCongelateurDto::fromEntity).isPresent()) {
            return result.map(TypeCongelateurDto::fromEntity)
                    .get();
        }
        return null;
    }
}
