package fr.kevinmilet.myfreezermanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import fr.kevinmilet.myfreezermanager.service.TypeProduitService;

@RestController
public class TypeProduitController {

    @Autowired
    TypeProduitService typeProduitService;
}
