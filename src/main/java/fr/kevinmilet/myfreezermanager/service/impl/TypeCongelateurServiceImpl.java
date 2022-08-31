package fr.kevinmilet.myfreezermanager.service.impl;

import fr.kevinmilet.myfreezermanager.entity.TypeCongelateur;
import fr.kevinmilet.myfreezermanager.repository.TypeCongelateurRepository;
import fr.kevinmilet.myfreezermanager.service.TypeCongelateurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<TypeCongelateur> getAllTypeCongelateur() {
        return typeCongelateurRepository.findAll();
    }

    @Override
    public TypeCongelateur createTypeCongelateur(TypeCongelateur typeCongelateur) {
        return typeCongelateurRepository.save(typeCongelateur);
    }

    @Override
    public TypeCongelateur updateTypeCongelateur(Long id, TypeCongelateur typeCongelateurRequest) throws Exception {
        TypeCongelateur typeCongelateur = typeCongelateurRepository.findById(id).orElseThrow(Exception::new);

        typeCongelateur.setNom(typeCongelateurRequest.getNom());
        return typeCongelateurRepository.save(typeCongelateur);
    }

    @Override
    public void deleteTypeCongelateur(Long id) throws Exception {
        TypeCongelateur typeCongelateur = typeCongelateurRepository.findById(id).orElseThrow(Exception::new);

        typeCongelateurRepository.delete(typeCongelateur);
    }

    @Override
    public TypeCongelateur getTypeCongelateurById(Long id) {
        Optional<TypeCongelateur> result = typeCongelateurRepository.findById(id);

        if (result.isPresent()) {
            return result.get();
        }

        return null;
    }
}
