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
import javax.ejb.Schedule;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import session.ArticlesFacade;
import session.EncheresFacade;
import session.PromotionsFacade;
import session.UsersFacade;

/**
 *
 * @author laura
 */
@Named(value = "promotions")
@RequestScoped
public class Promotions {
    
    @Inject
    PromotionsFacade promotions;
    
    public Promotions() {
    }
    
    public entity.Promotions getOne(){
        return promotions.findAll().get(promotions.findAll().size()-3);
    }

    public entity.Promotions getTwo(){
        return promotions.findAll().get(promotions.findAll().size()-2);
    }
    
    public entity.Promotions getThree(){
        return promotions.findAll().get(promotions.findAll().size()-1);
    }    
}
