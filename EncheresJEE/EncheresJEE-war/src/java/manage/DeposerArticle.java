/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import cookies.CookieGestion;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import entity.Articles;
import entity.Categories;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import session.ArticlesFacade;
import session.CategoriesFacade;
import session.UsersFacade;

/**
 *
 * @author laura
 */
@Named(value = "depos")
@RequestScoped
public class DeposerArticle {
    
    @Inject 
    ArticlesFacade article;
    @Inject 
    UsersFacade utilisateur;
    @Inject 
    CategoriesFacade categorie;
    
    Articles articleActif;
    
    public DeposerArticle(){

    }
    
    @PostConstruct
    public void setInitializeValue(){
        this.articleActif = new Articles();
        String id = CookieGestion.getInstance().getCookie("id").getValue();
        this.articleActif.setIdSellUsers(utilisateur.find(Integer.parseInt(id)));
        this.articleActif.setIdBuyUsers(null);
        this.articleActif.setNom("");
        this.articleActif.setDescription("");
        this.articleActif.setPrixInit(0);
        this.articleActif.setDateFin(Date.valueOf("2020-01-01"));
        this.articleActif.setPrixMax(0);
        this.articleActif.setVisible(true);
        this.articleActif.setNomCategorie(categorie.findByName("vase"));
    }
    
    public Articles getArticle(){
        return articleActif;
    }
    
    public void setArticle(Articles art){
        article.edit(art);
    }
    
    public String getIdSellUsers(){
        return "" + this.articleActif.getIdSellUsers();
    }
    
    public String getIdBuyUsers(){
        return "" + this.articleActif.getIdBuyUsers();
    }
    
    public String getNom(){
        return this.articleActif.getNom();
    }
    
    public String getDescription(){
        return this.articleActif.getDescription();
    }
    
    public String getPrixInit(){
        return "" + this.articleActif.getPrixInit();
    }
    
    public String getDateFin(){
        return "" + this.articleActif.getDateFin();
    }
    
    public String getPrixMax(){
        return "" + this.articleActif.getPrixMax();
    }
    
    public String getVisible(){
        return "" + this.articleActif.getVisible();
    }
    
    public String getCategorie(){
        return "" + this.articleActif.getNomCategorie();
    }
    
    public void setIdSellUsers(String id){
        this.articleActif.setIdSellUsers(utilisateur.find(Integer.parseInt(id)));
    }
    
    public void setIdBuyUsers(String id){
        this.articleActif.setIdBuyUsers(utilisateur.find(Integer.parseInt(id)));
    }
    
    public void setNom(String nom){
        this.articleActif.setNom(nom);
    }
    
    public void setDescription(String desc){
        this.articleActif.setDescription(desc);
    }
    
    public void setPrixInit(String p){
        this.articleActif.setPrixInit(Integer.parseInt(p));
        if(Integer.parseInt(p) > this.articleActif.getPrixMax()){
            this.articleActif.setPrixMax(Integer.parseInt(p)); 
        }
    }
    
    public void setDateFin(String d){
        this.articleActif.setDateFin(Date.valueOf(d));
    }

    public void setPrixMax(String p){
        this.articleActif.setPrixMax(Integer.parseInt(p));
    }
    
    public void setVisible(String v){
        this.articleActif.setVisible(Boolean.parseBoolean(v));
    }
    
    public void setCategorie(String cat){
        this.articleActif.setNomCategorie(categorie.findByName(cat));
    }
    
    public List<String> getAllCategories(){
        List<String> ret = new ArrayList();
        for(Categories cat : categorie.findAll()){
            ret.add(cat.getNom());
        }
        return ret;
    }
    
    public String validationDepos(){
        article.create(articleActif);
        return "Article " + articleActif.getNom()+" déposé !";
    }
}
