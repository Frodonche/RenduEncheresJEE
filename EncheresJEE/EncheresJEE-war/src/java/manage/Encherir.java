/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import cookies.CookieGestion;
import entity.Encheres;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import session.ArticlesFacade;
import session.EncheresFacade;
import session.UsersFacade;

/**
 *
 * @author laura
 */
@Named(value = "encherir")
@RequestScoped
public class Encherir {
    
    @Inject
    EncheresFacade enchere;  
    @Inject
    UsersFacade utilisateur;
    @Inject
    ArticlesFacade article;
    
    Encheres enchereActif;
    private String valeurActuelle;    
    
    public Encherir() {
        this.valeurActuelle = "0";
    }
    
    public String getValeurActuelle() {
        return valeurActuelle;
    }

    public void setValeurActuelle(String valeurActuelle) {
        this.valeurActuelle = valeurActuelle;
    }
        
    public String encherir(String idBuy){
        entity.Encheres encTmp = new entity.Encheres();
        entity.Articles artTmp = article.find(Integer.parseInt(CookieGestion.getInstance().getCookie("article").getValue()));
        encTmp.setIdArticles(artTmp);
        encTmp.setIdUsers(utilisateur.find(Integer.parseInt(idBuy)));
        encTmp.setValue(Integer.parseInt(this.valeurActuelle));
        enchere.create(encTmp);
        artTmp.setPrixMax(artTmp.getPrixMax() + Integer.parseInt(this.valeurActuelle));
        artTmp.setIdBuyUsers(utilisateur.find(Integer.parseInt(idBuy)));
        article.edit(artTmp);
        
        return "listArticles?faces-redirect=true";
    }
    
}
