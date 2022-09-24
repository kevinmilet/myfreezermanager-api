package fr.kevinmilet.myfreezermanager.dto;

import fr.kevinmilet.myfreezermanager.entity.TypeProduit;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author k.milet
 */
@Data
@Builder
@Slf4j
public class TypeProduitDto {

    private Long id;
    private String nom;

    public static TypeProduitDto fromEntity(TypeProduit typeProduit) {
        if (typeProduit == null) {
            log.error("Type produit null");
            return null;
        }

        return TypeProduitDto.builder()
                .id(typeProduit.getId())
                .nom(typeProduit.getNom())
                .build();
    }

    public static TypeProduit toEntity(TypeProduitDto dto) {
        if (dto == null) {
            log.error("Type produit dto null");
            return null;
        }

        TypeProduit typeProduit = new TypeProduit();
        typeProduit.setId(dto.getId());
        typeProduit.setNom(dto.getNom());

        return typeProduit;
    }
}
