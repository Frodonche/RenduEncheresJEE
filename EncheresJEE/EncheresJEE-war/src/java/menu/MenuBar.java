/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;


import cookies.CookieGestion;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


@Named(value = "menubar")
@RequestScoped
public class MenuBar {

    private List<NavBarElement> listeNavBar;
    private String login;
    private String id;

    /**
     * Il faudra nettoyer les classes du ejb
     */
    public MenuBar() {
        this.listeNavBar = new ArrayList<>();
        if (CookieGestion.getInstance().getCookie("login") == null) {
            this.listeNavBar.add(new NavBarElement("Accueil","index.xhtml"));
            this.listeNavBar.add(new NavBarElement("Inscription","inscription.xhtml"));
            this.listeNavBar.add(new NavBarElement("Voir les articles","listArticles.xhtml"));
            this.listeNavBar.add(new NavBarElement("Connexion","login.xhtml"));
        } else {
            this.listeNavBar.add(new NavBarElement("Accueil","index.xhtml"));
            this.listeNavBar.add(new NavBarElement("Déposer un article","deposerArticle.xhtml"));
            this.listeNavBar.add(new NavBarElement("Voir les articles","listArticles.xhtml"));
            this.login = CookieGestion.getInstance().getCookie("login").getValue();
            this.id = CookieGestion.getInstance().getCookie("id").getValue();
        }
            
        
    }

    public List<NavBarElement> getListeNavBar() {
        return listeNavBar;
    }

    public void setListeNavBar(List<NavBarElement> listeNavBar) {
        this.listeNavBar = listeNavBar;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    
}