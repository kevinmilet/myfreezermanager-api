package fr.kevinmilet.myfreezermanager.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.kevinmilet.myfreezermanager.entity.Congelateur;
import fr.kevinmilet.myfreezermanager.entity.TypeCongelateur;
import fr.kevinmilet.myfreezermanager.entity.Utilisateur;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

/**
 * @author k.milet
 */
@Data
@Builder
public class CongelateurDto {

    private Long id;
    private String nom;
    @JsonIgnore
    private Utilisateur utilisateur;
    private Instant dateCreation;
    private Instant dateMaj;
    private String idCongelateur;
    private TypeCongelateur typeCongelateur;

    public static CongelateurDto fromEntity(Congelateur congelateur) {
        if (congelateur == null) {
            return null;
        }

        return CongelateurDto.builder()
                .id(congelateur.getId())
                .nom(congelateur.getNom())
                .dateCreation(congelateur.getDateCreation())
                .dateMaj(congelateur.getDateMaj())
                .idCongelateur(congelateur.getIdCongelateur())
                .typeCongelateur(congelateur.getTypeCongelateur())
                .build();
    }

    public static Congelateur toEntity(CongelateurDto congelateurDto) {
        if (congelateurDto == null) {
            return null;
        }

        Congelateur congelateur = new Congelateur();
        congelateur.setId(congelateurDto.getId());
        congelateur.setNom(congelateurDto.getNom());
        congelateur.setDateCreation(congelateurDto.getDateCreation());
        congelateur.setDateMaj(congelateurDto.getDateMaj());
        congelateur.setIdCongelateur(congelateurDto.getIdCongelateur());
        congelateur.setTypeCongelateur(congelateurDto.getTypeCongelateur());

        return congelateur;
    }

    /*public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Instant getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Instant dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Instant getDateMaj() {
        return dateMaj;
    }

    public void setDateMaj(Instant dateMaj) {
        this.dateMaj = dateMaj;
    }

    public String getIdCongelateur() {
        return idCongelateur;
    }

    public void setIdCongelateur(String idCongelateur) {
        this.idCongelateur = idCongelateur;
    }

    public TypeCongelateur getTypeCongelateur() {
        return typeCongelateur;
    }

    public void setTypeCongelateur(TypeCongelateur typeCongelateur) {
        this.typeCongelateur = typeCongelateur;
    }*/

}
