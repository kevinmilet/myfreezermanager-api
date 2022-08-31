package fr.kevinmilet.myfreezermanager.dto;

import fr.kevinmilet.myfreezermanager.entity.Role;

import java.time.Instant;

public class UtilisateurDto {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String idCompte;
    private Role role;
    private Instant created_at;
    private Instant updated_at;
    private String token;
    private Boolean password_request;

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

    public Role getRole() {
	return role;
    }

    public void setRole(Role role) {
	this.role = role;
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
}
