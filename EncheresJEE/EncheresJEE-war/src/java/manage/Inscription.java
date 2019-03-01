/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import entity.Users;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import session.UsersFacade;

/**
 *
 * @author cirstea
 */
@Named(value = "inscription")
@RequestScoped
public class Inscription {

    @Inject 
    UsersFacade utilisateur;

    Users utilisateurActif;  
    
    /**
     * Creates a new instance of Hello
     */
    public Inscription() {
        utilisateurActif = new Users();
        utilisateurActif.setLogin("");
        utilisateurActif.setPass("");
        utilisateurActif.setAdresse("");
        utilisateurActif.setNom("");
        utilisateurActif.setPrenom("");
        utilisateurActif.setIdBancaire("");
        utilisateurActif.setNbAbandon(0);
        utilisateurActif.setConnecte(false);
    }

    public Users getUtilisateur() {
        return utilisateurActif;
    }

    public void setUtilisateur(Users user) {
        utilisateur.edit(user);
    }

    public String getLogin() {
        return utilisateurActif.getLogin();
    }

    public void setLogin(String login) {
        utilisateurActif.setLogin(login);
    }

    public String getPass() {
        return utilisateurActif.getPass();
    }

    public void setPass(String pass) {
        utilisateurActif.setPass(pass);
    }

    public String getNom() {
        return utilisateurActif.getNom();
    }

    public void setNom(String nom) {
        utilisateurActif.setNom(nom);
    }

    public String getPrenom() {
        return utilisateurActif.getPrenom();
    }

    public void setPrenom(String prenom) {
        utilisateurActif.setPrenom(prenom);
    }

    public String getAdresse() {
        return utilisateurActif.getAdresse();
    }

    public void setAdresse(String adresse) {
        utilisateurActif.setAdresse(adresse);
    }

    public String getId_bancaire() {
        return utilisateurActif.getIdBancaire();
    }

    public void setId_bancaire(String id_bancaire) {
        utilisateurActif.setIdBancaire(id_bancaire);
    }

    public boolean isConnecte() {
        return utilisateurActif.getConnecte();
    }

    public void setConnecte(boolean connecte) {
        utilisateurActif.setConnecte(connecte);
    }

    public int getNb_abandon() {
        return utilisateurActif.getNbAbandon();
    }

    public void setNb_abandon(int nb_abandon) {
        utilisateurActif.setNbAbandon(nb_abandon);
    }
    
    public String inscrire() { 
        utilisateur.create(utilisateurActif);
        return "Utilisateur " + utilisateurActif.getLogin() + " inscrit !";  
    }
    
}