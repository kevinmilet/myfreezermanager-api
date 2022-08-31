package fr.kevinmilet.myfreezermanager.service.impl;

import fr.kevinmilet.myfreezermanager.entity.TypeProduit;
import fr.kevinmilet.myfreezermanager.repository.TypeProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.kevinmilet.myfreezermanager.service.TypeProduitService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TypeProduitServiceImpl implements TypeProduitService {

    private final TypeProduitRepository typeProduitRepository;

    @Autowired
    public TypeProduitServiceImpl(TypeProduitRepository typeProduitRepository) {
        this.typeProduitRepository = typeProduitRepository;
    }

    @Override
    public List<TypeProduit> getAllTypeProduit() {
        return (List<TypeProduit>) typeProduitRepository.findAll();
    }

    @Override
    public TypeProduit createTypeProduit(TypeProduit typeProduit) {
        return typeProduitRepository.save(typeProduit);
    }

    @Override
    public TypeProduit updateTypeProduit(Long id, TypeProduit typeProduitRequest) throws Exception {
        TypeProduit typeProduit = typeProduitRepository.findById(id).orElseThrow(Exception::new);

        typeProduit.setNom(typeProduitRequest.getNom());
        return typeProduitRepository.save(typeProduit);
    }

    @Override
    public void deleteTypeProduit(Long id) throws Exception {
        TypeProduit typeProduit = typeProduitRepository.findById(id).orElseThrow(Exception::new);

        typeProduitRepository.delete(typeProduit);
    }

    @Override
    public TypeProduit getTypeProduitById(Long id) {
        Optional<TypeProduit> result = typeProduitRepository.findById(id);

        if (result.isPresent()) {
            return result.get();
        }

        return null;
    }
}
