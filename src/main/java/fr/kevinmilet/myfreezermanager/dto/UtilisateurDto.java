package fr.kevinmilet.myfreezermanager.dto;

import fr.kevinmilet.myfreezermanager.entity.Utilisateur;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

/**
 * @author k.milet
 */
@Data
@Builder
public class UtilisateurDto {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String idCompte;
    private Instant created_at;
    private Instant updated_at;
    private String token;
    private Boolean password_request;
    private Boolean isAdmin;
    private Boolean isActive;

    public static UtilisateurDto fromEntity(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }

        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .email(utilisateur.getEmail())
                .password(utilisateur.getPassword())
                .idCompte(utilisateur.getIdCompte())
                .created_at(utilisateur.getCreated_at())
                .updated_at(utilisateur.getUpdated_at())
                .token(utilisateur.getToken())
                .password_request(utilisateur.getPassword_request())
                .isAdmin(utilisateur.getAdmin())
                .isActive(utilisateur.getActive())
                .build();
    }

    public static Utilisateur toEntity(UtilisateurDto dto) {
        if (dto == null) {
            return null;
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(dto.getId());
        utilisateur.setNom(dto.getNom());
        utilisateur.setPrenom(dto.getPrenom());
        utilisateur.setEmail(dto.getEmail());
        utilisateur.setPassword(dto.getPassword());
        utilisateur.setPassword_request(dto.getPassword_request());
        utilisateur.setIdCompte(dto.getIdCompte());
        utilisateur.setCreated_at(dto.getCreated_at());
        utilisateur.setUpdated_at(dto.getUpdated_at());
        utilisateur.setToken(dto.getToken());
        utilisateur.setPassword_request(dto.getPassword_request());
        utilisateur.setAdmin(dto.getIsAdmin());
        utilisateur.setActive(dto.getActive());

        return utilisateur;
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
