package fr.kevinmilet.myfreezermanager.entity;

import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nom;
    private int quantité;

    @NotBlank
    @ManyToOne
    private TypeProduit typeProduit;

    private LocalDate dateAjout;
    private LocalDate dateRetrait;

    @OneToOne
    private Congelateur congelateur;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @JsonIgnore
    private Instant dateCreation;

    @LastModifiedDate
    @JsonIgnore
    private Instant dateMaj;

    private String idProduit;

    public Produit() {

    }

    public Produit(String nom, int quantité, TypeProduit typeProduit, LocalDate dateAjout, LocalDate dateRetrait,
	    Congelateur congelateur, String idProduit) {
	this.nom = nom;
	this.quantité = quantité;
	this.typeProduit = typeProduit;
	this.dateAjout = dateAjout;
	this.dateRetrait = dateRetrait;
	this.congelateur = congelateur;
	this.idProduit = idProduit;
    }

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

    public int getQuantité() {
	return quantité;
    }

    public void setQuantité(int quantité) {
	this.quantité = quantité;
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
