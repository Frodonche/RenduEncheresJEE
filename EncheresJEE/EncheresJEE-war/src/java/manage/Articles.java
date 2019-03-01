/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import cookies.CookieGestion;
import java.util.ArrayList;
import java.util.List;
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
@Named(value = "articles")
@RequestScoped
public class Articles {

    @Inject
    ArticlesFacade article;
    @Inject
    UsersFacade utilisateur;
    @Inject
    EncheresFacade enchere;

    private ArticleDef def;
    private String enchereActuel;

    public Articles() {
        enchereActuel = "0";
    }

    public String getEnchereActuel() {
        return this.enchereActuel;
    }

    public void setEnchereActuel(String enchere) {
        this.enchereActuel = enchere;
    }

    public String encherir(String id) {
        int prix = Integer.parseInt(enchereActuel);
        String login = CookieGestion.getInstance().getCookie("login").getValue();

        //modif article
        entity.Articles tmp = article.find(Integer.parseInt(id));
        tmp.setPrixMax(prix);
        article.edit(tmp);

        //creer enchere
        entity.Encheres encTmp = new entity.Encheres();
        encTmp.setIdArticles(tmp);
        encTmp.setIdUsers(utilisateur.findByLogin(login));
        encTmp.setValue(prix);
        enchere.create(encTmp);

        return "listArticles?faces-redirect=true";
    }

    public List<ArticleDef> viewAll() {
        List<entity.Articles> arts = article.findAll();
        List<ArticleDef> res = new ArrayList();
        String s1, s2, s3, s4, s5, s6, s7, s8, s9, s10;

        for (entity.Articles art : arts) {
            s1 = art.getId().toString();
            s2 = art.getIdSellUsers().getId().toString();
            if (art.getIdBuyUsers() != null) {
                s3 = art.getIdBuyUsers().getId().toString();
            } else {
                s3 = "null";
            }
            s4 = art.getNom();
            s5 = art.getDescription();
            s6 = art.getPrixInit().toString();
            s7 = art.getDateFin().toString();
            if (art.getPrixMax() != null) {
                s8 = art.getPrixMax().toString();
            } else {
                s8 = art.getPrixInit().toString();
            }
            s9 = art.getVisible().toString();
            s10 = art.getNomCategorie().getNom();
            res.add(new ArticleDef(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, false, false, this));
        }
        return res;
    }

    public ArticleDef getOne(String id) {
        entity.Articles art = article.find(Integer.parseInt(id));
        String s1, s2, s3, s4, s5, s6, s7, s8, s9, s10;

        s1 = art.getId().toString();
        s2 = art.getIdSellUsers().getId().toString();
        if (art.getIdBuyUsers() != null) {
            s3 = art.getIdBuyUsers().getId().toString();
        } else {
            s3 = "null";
        }
        s4 = art.getNom();
        s5 = art.getDescription();
        s6 = art.getPrixInit().toString();
        s7 = art.getDateFin().toString();
        if (art.getPrixMax() != null) {
            s8 = art.getPrixMax().toString();
        } else {
            s8 = art.getPrixInit().toString();
        }
        s9 = art.getVisible().toString();
        s10 = art.getNomCategorie().getNom();
        def = new ArticleDef(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, false, false, this);
        return def;
    }

    public ArticleDef getOne() {
        return def;
    }

    public String validationEncherir(String id) {
        getOne(id);
        CookieGestion.getInstance().createCookie("article", id, 1800);
        return "validationEncherir.xhtml";
    }
    
    public String validationEncherir(Integer id) {
        String ide = "" + id;
        getOne(ide);
        CookieGestion.getInstance().createCookie("article", ide, 1800);
        return "validationEncherir.xhtml";
    }

    public List<String> getCat() {
        return null;
    }

    public String viewNameUserById(String id) {
        if (id.equals("null"))
            return "Aucun enchérisseur";
        return utilisateur.find(Integer.parseInt(id)).getLogin();
    }

    public List<ArticleDef> viewArticlesUserLogged() {
        List<entity.Articles> arts = article.findAll();
        List<ArticleDef> res = new ArrayList();
        String s1, s2, s3, s4, s5, s6, s7, s8, s9, s10;
        int id = Integer.parseInt(CookieGestion.getInstance().getCookie("id").getValue());
        for (entity.Articles art : arts) {
            if (art.getIdSellUsers().getId() == id) {
                s1 = art.getId().toString();
                s2 = art.getIdSellUsers().getId().toString();
                if (art.getIdBuyUsers() != null) {
                    s3 = art.getIdBuyUsers().getId().toString();
                } else {
                    s3 = "null";
                }
                s4 = art.getNom();
                s5 = art.getDescription();
                s6 = art.getPrixInit().toString();
                s7 = art.getDateFin().toString();
                if (art.getPrixMax() != null) {
                    s8 = art.getPrixMax().toString();
                } else {
                    s8 = art.getPrixInit().toString();
                }
                s9 = art.getVisible().toString();
                s10 = art.getNomCategorie().getNom();
                res.add(new ArticleDef(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, false, false, this));
            }
        }

        return res;
    }

    public String supprimerArticle(String id) {
        List<entity.Encheres> listeEncheres = enchere.findAll();
        for (entity.Encheres e : listeEncheres) {
            if (e.getIdArticles().getId() == Integer.parseInt(id)) {
                enchere.remove(e);
            }
        }    
        article.remove(article.find(Integer.parseInt(id)));
        
        return "mesArticles.xhtml?faces-redirect=true";
    }
}
