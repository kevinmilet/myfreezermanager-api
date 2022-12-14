package fr.kevinmilet.myfreezermanager.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Congelateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, message = "Le nom du congélateur ne peut pas être vide")
    private String nom;

    @ManyToOne
    private Utilisateur utilisateur;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @JsonIgnore
    private Instant dateCreation;

    @LastModifiedDate
    @JsonIgnore
    private Instant dateMaj;

    private String idCongelateur;

    public Congelateur() {

    }

    public Congelateur(String nom, Utilisateur utilisateur, String idCongelateur) {
	this.nom = nom;
	this.utilisateur = utilisateur;
	this.idCongelateur = idCongelateur;
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

    public String getIdCconglelateur() {
	return idCongelateur;
    }

    public void setIdCongelateur(String idCongelateur) {
	this.idCongelateur = idCongelateur;
    }

}
