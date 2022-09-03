package fr.kevinmilet.myfreezermanager.service.impl;

import fr.kevinmilet.myfreezermanager.entity.Congelateur;
import fr.kevinmilet.myfreezermanager.entity.Utilisateur;
import fr.kevinmilet.myfreezermanager.repository.CongelateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.kevinmilet.myfreezermanager.service.CongelateurService;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class CongelateurServiceImpl implements CongelateurService {

    private final CongelateurRepository congelateurRepository;

    @Autowired
    public CongelateurServiceImpl(CongelateurRepository congelateurRepository) {
        this.congelateurRepository = congelateurRepository;
    }

    @Override
    public List<Congelateur> getAllCongelateur() {
        return congelateurRepository.findAll();
    }

    @Override
    public Congelateur createCongelateur(Congelateur congelateur) {
        String uuid = UUID.randomUUID().toString();

        congelateur.setIdCongelateur(uuid);
        congelateur.setDateCreation(Instant.now());

        return congelateurRepository.save(congelateur);
    }

    @Override
    public Congelateur updateCongelateur(Long id, Congelateur congelateurRequest) throws Exception {
        Congelateur congelateur = congelateurRepository.findById(id).orElseThrow(Exception::new);

        congelateur.setNom(congelateurRequest.getNom());
        congelateur.setTypeCongelateur(congelateurRequest.getTypeCongelateur());
        congelateur.setDateMaj(Instant.now());

        return congelateurRepository.save(congelateur);
    }

    @Override
    public void deleteCongelateur(Long id) throws Exception {
        Congelateur congelateur = congelateurRepository.findById(id).orElseThrow(Exception::new);

        congelateurRepository.delete(congelateur);
    }

    @Override
    public Congelateur getCongelateurById(Long id) {
        Optional<Congelateur> result = congelateurRepository.findById(id);

        if (result.isPresent()) {
            return result.get();
        }

        return null;
    }

    @Override
    public List<Congelateur> getCongelateurByUtilisateur(Utilisateur utilisateur) {
        return null;
    }
}
