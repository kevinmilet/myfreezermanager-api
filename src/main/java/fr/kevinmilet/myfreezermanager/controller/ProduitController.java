package fr.kevinmilet.myfreezermanager.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import fr.kevinmilet.myfreezermanager.service.ProduitService;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class ProduitController {

    @Autowired
    ProduitService produitService;
}
