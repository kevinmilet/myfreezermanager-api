package fr.kevinmilet.myfreezermanager.dto;

import fr.kevinmilet.myfreezermanager.entity.Role;

public class UtilisateurDto {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String idCompte;
    private Role role;

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

}
