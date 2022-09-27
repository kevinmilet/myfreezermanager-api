package fr.kevinmilet.myfreezermanager.service.impl;

import fr.kevinmilet.myfreezermanager.dto.TypeProduitDto;
import fr.kevinmilet.myfreezermanager.entity.TypeProduit;
import fr.kevinmilet.myfreezermanager.repository.TypeProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.kevinmilet.myfreezermanager.service.TypeProduitService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TypeProduitServiceImpl implements TypeProduitService {

    private final TypeProduitRepository typeProduitRepository;

    @Autowired
    public TypeProduitServiceImpl(TypeProduitRepository typeProduitRepository) {
        this.typeProduitRepository = typeProduitRepository;
    }

    @Override
    public List<TypeProduitDto> getAllTypeProduit() {

        return typeProduitRepository.findAll().stream()
                .map(TypeProduitDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TypeProduitDto createTypeProduit(TypeProduitDto typeProduitDto) {

        return TypeProduitDto.fromEntity(
                typeProduitRepository.save(Objects.requireNonNull(TypeProduitDto.toEntity(typeProduitDto)))
        );
    }

    public TypeProduitDto updateTypeProduit(String id, TypeProduitDto typeProduitDto) {
        typeProduitRepository.updateTypeProduit(Long.valueOf(id), typeProduitDto.getNom());
        return typeProduitDto;
    }

    @Override
    public void deleteTypeProduit(Long id) {
        if (id == null) {
            log.error("Type Produit ID null");
            return;
        }
        Optional<TypeProduit> typeProduit = typeProduitRepository.findById(id);

        typeProduit.ifPresent(typeProduitRepository::delete);
    }

    @Override
    public TypeProduitDto getTypeProduitById(Long id) {
        if (id == null) {
            log.error("Type Produit ID is null");
            return null;
        }

        Optional<TypeProduit> result = typeProduitRepository.findById(id);

        if (result.map(TypeProduitDto::fromEntity).isPresent()) {
            return result.map(TypeProduitDto::fromEntity)
                    .get();
        }

        return null;
    }
}
