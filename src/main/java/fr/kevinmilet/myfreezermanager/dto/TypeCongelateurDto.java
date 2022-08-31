package fr.kevinmilet.myfreezermanager.dto;

import javax.validation.constraints.NotBlank;

/**
 * @author k.milet
 */
public class TypeCongelateurDto {

    private Long id;
    private String nom;

    public Long getId() {
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
}
