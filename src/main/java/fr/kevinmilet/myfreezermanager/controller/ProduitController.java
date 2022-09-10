package fr.kevinmilet.myfreezermanager.controller;

import fr.kevinmilet.myfreezermanager.dto.CongelateurDto;
import fr.kevinmilet.myfreezermanager.dto.ProduitDto;
import fr.kevinmilet.myfreezermanager.entity.Congelateur;
import fr.kevinmilet.myfreezermanager.entity.Produit;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.kevinmilet.myfreezermanager.service.ProduitService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@SecurityRequirement(name = "bearerAuth")
public class ProduitController {

    private final ProduitService produitService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProduitController(ProduitService produitService, ModelMapper modelMapper) {
        this.produitService = produitService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/produits")
    public List<ProduitDto> getAllProduits() {
        return produitService.getAllProduits().stream().map(produit -> modelMapper.map(produit, ProduitDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/mes_produits")
    public List<ProduitDto> getProduitsUtilisateur(Principal principal) {
        return  produitService.getProduitUtilisateur(principal).stream().map(produit -> modelMapper.map(produit, ProduitDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/mes_produits/congelateur/{congelateurId}")
    public List<ProduitDto> getProduitsCongelateur(@PathVariable("congelateurId") String id) {
        return  produitService.getProduitCongelateur(Long.parseLong(id)).stream().map(produit -> modelMapper.map(produit, ProduitDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/produit/{id}")
    public ResponseEntity<ProduitDto> getProduitById(@PathVariable(name = "id") String id) {
        Produit produit = produitService.getProduitById(Long.parseLong(id));
        ProduitDto response = modelMapper.map(produit, ProduitDto.class);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/produit/create")
    public ResponseEntity<ProduitDto> createProduit(@RequestBody ProduitDto produitDto, Principal principal) {

        // convert DTO to entity
        Produit request = modelMapper.map(produitDto, Produit.class);

        ResponseEntity produit = produitService.createProduit(request, principal);

        // convert entity to DTO
        ProduitDto response = modelMapper.map(produit, ProduitDto.class);

        return new ResponseEntity<ProduitDto>(response, HttpStatus.CREATED);
    }

    @PutMapping("/produit/update/{id}")
    public ResponseEntity<ProduitDto> updateProduit(@PathVariable(name = "id") String id,
                                                            @RequestBody ProduitDto produitDto) throws Exception {

        // convert DTO to Entity
        Produit request = modelMapper.map(produitDto, Produit.class);

        Produit produit = produitService.updateProduit(Long.parseLong(id), request);

        // entity to DTO
        ProduitDto response = modelMapper.map(produit, ProduitDto.class);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/produit/delete/{id}")
    public ResponseEntity<String> suppressionProduit(@PathVariable(name = "id") String id) throws Exception {
        produitService.deleteProduit(Long.parseLong(id));
        return new ResponseEntity<String>("Produit supprimé avec succes", HttpStatus.OK);
    }
}
