package fr.kevinmilet.myfreezermanager.controller;

import fr.kevinmilet.myfreezermanager.dto.TypeCongelateurDto;
import fr.kevinmilet.myfreezermanager.service.TypeCongelateurService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author k.milet
 */
@RestController
@SecurityRequirement(name = "bearerAuth")
public class TypeCongelateurController {

    private final TypeCongelateurService typeCongelateurService;

    @Autowired
    public TypeCongelateurController(TypeCongelateurService typeCongelateurService) {
        this.typeCongelateurService = typeCongelateurService;
    }

    @GetMapping("/types_congelateur")
    public List<TypeCongelateurDto> getAllTypeCongelateur() {
        return typeCongelateurService.getAllTypeCongelateur();
    }

    @GetMapping("/type_congelateur/{id}")
    public TypeCongelateurDto getTypeCongelateurById(@PathVariable(name = "id") String id) {
        return typeCongelateurService.getTypeCongelateurById(Long.valueOf(id));
    }

    @PostMapping("/type_congelateur/create")
    public TypeCongelateurDto createTypeCongelateurCongelateur(@RequestBody TypeCongelateurDto typeCongelateurDto) {
        return typeCongelateurService.createTypeCongelateur(typeCongelateurDto);
    }

    @PutMapping("/type_congelateur/update/{id}")
    public TypeCongelateurDto updateTypeCongelateur(@PathVariable(name="id") String id,
                                                            @RequestBody TypeCongelateurDto typeCongelateurDto) throws Exception {

        return typeCongelateurService.updateTypeCongelateur(Long.valueOf(id), typeCongelateurDto);
    }

    @DeleteMapping("/type_congelateur/delete/{id}")
    public void suppressionTypeCongelateur(@PathVariable(name = "id") String id) throws Exception {
        typeCongelateurService.deleteTypeCongelateur(Long.valueOf(id));
    }
}
