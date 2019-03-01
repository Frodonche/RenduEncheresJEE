/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import cookies.CookieGestion;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
@Named(value = "encheres")
@RequestScoped
public class Encheres {
    
    @Inject
    EncheresFacade enchere;  
    @Inject
    UsersFacade utilisateur;
    @Inject
    ArticlesFacade article;
    
    
    public Encheres() {
    }
    
    public List<entity.Encheres> viewAllNotTerminated(){
        Date when = new java.sql.Date(System.currentTimeMillis());
        List<entity.Encheres> ret = new ArrayList<entity.Encheres>();
        Integer id = utilisateur.findByLogin(CookieGestion.getInstance().getCookie("login").getValue()).getId();
        for(entity.Encheres ench : enchere.findAll()){
            if(Objects.equals(ench.getIdUsers().getId(), id)){//si l'user correspond a l'enchere
                if(ench.getIdArticles().getDateFin().after(when))
                    ret.add(ench);
            }
        }
        return ret;
    }
    
    public List<entity.Encheres> viewAllTerminated(){
        Date when = new java.sql.Date(System.currentTimeMillis());
        List<entity.Encheres> ret = new ArrayList<entity.Encheres>();
        Integer id = utilisateur.findByLogin(CookieGestion.getInstance().getCookie("login").getValue()).getId();
        for(entity.Encheres ench : enchere.findAll()){
            if(Objects.equals(ench.getIdUsers().getId(), id)){//si l'user correspond a l'enchere
                if(ench.getIdArticles().getDateFin().before(when))
                    ret.add(ench);
            }
        }
        return ret;
    }
    
    public List<entity.Encheres> viewAllWon() {
        Date when = new java.sql.Date(System.currentTimeMillis());
        List<entity.Encheres> ret = new ArrayList<entity.Encheres>();
        Integer id = utilisateur.findByLogin(CookieGestion.getInstance().getCookie("login").getValue()).getId();
        for(entity.Encheres ench : enchere.findAll()){
            if(Objects.equals(ench.getIdUsers().getId(), id)){//si l'user correspond a l'enchere
                if(ench.getIdArticles().getDateFin().before(when)) {
                    if (ench.getIdArticles().getIdBuyUsers().getId() == id)
                        ret.add(ench);
                }               
            }
        }
        return ret;
    }
    
    public List<entity.Encheres> viewAllLost() {
        Date when = new java.sql.Date(System.currentTimeMillis());
        List<entity.Encheres> ret = new ArrayList<entity.Encheres>();
        Integer id = utilisateur.findByLogin(CookieGestion.getInstance().getCookie("login").getValue()).getId();
        for(entity.Encheres ench : enchere.findAll()){
            if(Objects.equals(ench.getIdUsers().getId(), id)){//si l'user correspond a l'enchere
                if(ench.getIdArticles().getDateFin().before(when)) {
                    if (ench.getIdArticles().getIdBuyUsers().getId() != id)
                        ret.add(ench);
                }               
            }
        }
        return ret;
    }
    
    public String supprimerEnchere(String id) {
        entity.Encheres ench = enchere.find(Integer.parseInt(id));
        entity.Articles art = article.find(ench.getIdArticles().getId());
       
        entity.Encheres enchPrec = null; //Enchérisseur précédent
        List<entity.Encheres> listeEncheres = enchere.findAll();
        List<entity.Encheres> tmp = new ArrayList<>();
        List<entity.Encheres> garbage = new ArrayList<>();
        for (entity.Encheres e : listeEncheres) {
            if (e.getIdUsers().getId() == ench.getIdUsers().getId()) {
                garbage.add(e);
            } else {
                if (e.getIdArticles().getId() == art.getId())
                    tmp.add(e);
            }
        }     
        art.setPrixMax(art.getPrixMax() - ench.getValue());
        //enchere.remove(ench);
        for (entity.Encheres e : garbage) {
            enchere.remove(e);
        }
        if (tmp.size() > 1) {
            for (int i = 1; i < tmp.size(); i++) {
                enchPrec = tmp.get(0);
                if (enchPrec.getId() < tmp.get(i).getId())
                    enchPrec = tmp.get(i);
            }
            art.setIdBuyUsers(enchPrec.getIdUsers());
        } else {
            art.setIdBuyUsers(null);
        }
        article.edit(art);
        
        return "encheres.xhtml?faces-redirect=true";
    }
}
