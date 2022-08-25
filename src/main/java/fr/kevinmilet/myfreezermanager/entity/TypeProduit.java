package fr.kevinmilet.myfreezermanager.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TypeProduit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nom;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @JsonIgnore
    private Instant dateCreation;

    @LastModifiedDate
    @JsonIgnore
    private Instant dateMaj;

    public TypeProduit() {
    }

    public TypeProduit(String nom) {
	this.nom = nom;
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

}
