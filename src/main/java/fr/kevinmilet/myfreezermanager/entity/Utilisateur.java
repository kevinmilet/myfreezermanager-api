package fr.kevinmilet.myfreezermanager.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nom;
    @NotBlank
    private String prenom;
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @JsonIgnore
    private Instant created_at;

    @LastModifiedDate
    @JsonIgnore
    private Instant updated_at;

    private String idCompte;

    @ManyToOne
    private Role role;

    public Utilisateur() {

    }

    public Utilisateur(String nom, String prenom, String email, String idCompte) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.idCompte = idCompte;
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

    public String getPrenom() {
	return prenom;
    }

    public void setPrenom(String prenom) {
	this.prenom = prenom;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public Instant getDateCreation() {
	return created_at;
    }

    public void setDateCreation(Instant created_at) {
	this.created_at = created_at;
    }

    public Instant getDateMaj() {
	return updated_at;
    }

    public void setDateMaj(Instant updated_at) {
	this.updated_at = updated_at;
    }

    public String getIdCompte() {
	return idCompte;
    }

    public void setIdCompte(String idCompte) {
	this.idCompte = idCompte;
    }

    public Role getRole() {
	return role;
    }

    public void setRole(Role role) {
	this.role = role;
    }

}
