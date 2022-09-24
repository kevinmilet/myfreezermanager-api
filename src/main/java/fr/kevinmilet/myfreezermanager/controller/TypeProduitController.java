package fr.kevinmilet.myfreezermanager.controller;

import fr.kevinmilet.myfreezermanager.dto.TypeProduitDto;
import fr.kevinmilet.myfreezermanager.service.TypeProduitService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class TypeProduitController {

    private final TypeProduitService typeProduitService;

    @Autowired
    public TypeProduitController(TypeProduitService typeProduitService) {
        this.typeProduitService = typeProduitService;
    }

    @GetMapping("/types_produit")
    public List<TypeProduitDto> getAllTypeProduit() {
        return typeProduitService.getAllTypeProduit();
    }

    @GetMapping("/types_produit/{id}")
    public TypeProduitDto getTypeCongelateurById(@PathVariable(name = "id") Long id) {
        return typeProduitService.getTypeProduitById(id);
    }

    @PostMapping("/types_produit/create")
    public TypeProduitDto createTypeProduit(@RequestBody TypeProduitDto typeProduitDto) {

        return typeProduitService.createTypeProduit(typeProduitDto);
    }

    @PutMapping("/types_produit/update/{id}")
    public TypeProduitDto updateTypePrtoduit(@PathVariable(name = "id") String id,
                                             @RequestBody TypeProduitDto typeProduitDto) throws Exception {
        return typeProduitService.updateTypeProduit(id, typeProduitDto);
    }

    @DeleteMapping("/types_produit/delete/{id}")
    public void suppressionTypeProduit(@PathVariable(name = "id") String id) throws Exception {
        typeProduitService.deleteTypeProduit(Long.valueOf(id));
    }
}
