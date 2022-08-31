package fr.kevinmilet.myfreezermanager.controller;

import fr.kevinmilet.myfreezermanager.entity.TypeProduit;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.kevinmilet.myfreezermanager.service.TypeProduitService;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class TypeProduitController {

    private final TypeProduitService typeProduitService;
    private final ModelMapper modelMapper;

    @Autowired
    public TypeProduitController(TypeProduitService typeProduitService, ModelMapper modelMapper) {
        this.typeProduitService = typeProduitService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/types_produits")
    public List<TypeProduit> getAllTypeProduit() {
        return null;
    }
}
