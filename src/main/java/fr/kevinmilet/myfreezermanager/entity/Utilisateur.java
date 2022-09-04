package fr.kevinmilet.myfreezermanager.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

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
    private Instant created_at;

    @LastModifiedDate
    private Instant updated_at;

    @NotBlank
    private String idCompte;

    @Column(columnDefinition = "boolean default false")
    private Boolean isAdmin;
    @Column(columnDefinition = "boolean default true")
    private Boolean isActive;

    private String token;

    private Boolean password_request;

    public Utilisateur() {

    }

    public Utilisateur(String nom, String prenom, String email, String idCompte, Boolean isAdmin, Boolean isActive) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.idCompte = idCompte;
        this.isAdmin = isActive;
        this.isActive = isActive;
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

    public String getIdCompte() {
	return idCompte;
    }

    public void setIdCompte(String idCompte) {
	this.idCompte = idCompte;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getPassword_request() {
        return password_request;
    }

    public void setPassword_request(Boolean password_request) {
        this.password_request = password_request;
    }

    public Instant getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Instant created_at) {
        this.created_at = created_at;
    }

    public Instant getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Instant updated_at) {
        this.updated_at = updated_at;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
