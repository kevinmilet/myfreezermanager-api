package fr.kevinmilet.myfreezermanager.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.kevinmilet.myfreezermanager.entity.TypeCongelateur;
import fr.kevinmilet.myfreezermanager.entity.Utilisateur;
import java.time.Instant;

/**
 * @author k.milet
 */
public class CongelateurDto {

    private Long id;
    private String nom;
    @JsonIgnore
    private Utilisateur utilisateur;
    private Instant dateCreation;
    private Instant dateMaj;
    private String idCongelateur;
    private TypeCongelateur typeCongelateur;

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
    }
}
