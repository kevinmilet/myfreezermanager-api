package fr.kevinmilet.myfreezermanager.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.kevinmilet.myfreezermanager.entity.Congelateur;
import fr.kevinmilet.myfreezermanager.entity.TypeProduit;
import fr.kevinmilet.myfreezermanager.entity.Utilisateur;

import java.time.Instant;
import java.time.LocalDate;

/**
 * @author k.milet
 */
public class ProduitDto {

    private Long id;
    private String nom;
    private int quantite;
    private TypeProduit typeProduit;
    private LocalDate dateAjout;
    private LocalDate dateRetrait;
    private Congelateur congelateur;
    @JsonIgnore
    private Utilisateur utilisateur;
    private Instant dateCreation;
    private Instant dateMaj;
    private String idProduit;

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

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public TypeProduit getTypeProduit() {
        return typeProduit;
    }

    public void setTypeProduit(TypeProduit typeProduit) {
        this.typeProduit = typeProduit;
    }

    public LocalDate getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(LocalDate dateAjout) {
        this.dateAjout = dateAjout;
    }

    public LocalDate getDateRetrait() {
        return dateRetrait;
    }

    public void setDateRetrait(LocalDate dateRetrait) {
        this.dateRetrait = dateRetrait;
    }

    public Congelateur getCongelateur() {
        return congelateur;
    }

    public void setCongelateur(Congelateur congelateur) {
        this.congelateur = congelateur;
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

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }
}
