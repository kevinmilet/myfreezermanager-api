package fr.kevinmilet.myfreezermanager.dto;

import fr.kevinmilet.myfreezermanager.entity.TypeCongelateur;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author k.milet
 */
@Data
@Builder
@Slf4j
public class TypeCongelateurDto {

    private Long id;
    private String nom;

    public static TypeCongelateurDto fromEntity(TypeCongelateur typeCongelateur) {
        if (typeCongelateur == null) {
            log.error("Type Congelateur null");
            return null;
        }

        return TypeCongelateurDto.builder()
                .id(typeCongelateur.getId())
                .nom(typeCongelateur.getNom())
                .build();
    }

    public static TypeCongelateur toEntity(TypeCongelateurDto dto) {
        if (dto == null) {
            log.error("Type congelateur dto is null");
            return null;
        }

        TypeCongelateur typeCongelateur = new TypeCongelateur();
        typeCongelateur.setId(dto.getId());
        typeCongelateur.setNom(dto.getNom());

        return  typeCongelateur;
    }
}
