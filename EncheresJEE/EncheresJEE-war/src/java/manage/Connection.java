/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import cookies.CookieGestion;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import session.UsersFacade;

/**
 *
 * @author gui
 */
@Named(value = "connection")
@RequestScoped
public class Connection {

    @EJB 
    private UsersFacade utilisateur;

    private String login;
    private String pass;
    
    private boolean estConnecte;
    private String userConnecte;
   
    private static String failureMessage = "Mauvaise combinaison login / mot de passe";
    private boolean failure;
    
    
    /**
     * Creates a new instance of Hello
     */
    public Connection() {
        login = "";
        pass = "";
        failure = false;
    }

    public UsersFacade getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(UsersFacade utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public String messageFailure(){
        if(failure)
            return failureMessage;
        else
            return " ";
    }
    
    public String connecter() {
        boolean success = utilisateur.connecter(login, pass);  
        
        String redirectionPage;
        
        // Si la connection a reussi, renvoie à l'accueil
        // Sinon avertir que la connection a échoué
        if(success){
            failure = false;
            //utilisateur.setConnecte(true);
            
            int id = utilisateur.findByLogin(login).getId();
                        
            CookieGestion.getInstance().createCookie("login", login, 1800);
            CookieGestion.getInstance().createCookie("id", ""+id, 1800);
            
            redirectionPage = "index?faces-redirect=true";
        } else {
            failure = true;
            redirectionPage = "login?faces-redirect=true";
        }
        
        return redirectionPage;
    }
    
    public String deconnecter() {
        utilisateur.deconnecter(login);
        
        CookieGestion.getInstance().deleteCookie("login");
        CookieGestion.getInstance().deleteCookie("id");
        
        return "login?faces-redirect=true";
    }
    
    public boolean estConnecte(){
        return utilisateur.findByLogin(login).getConnecte();
    }
    
    public String getLoginConnecte(){
        return utilisateur.findByLogin(login).getLogin();
    }

}