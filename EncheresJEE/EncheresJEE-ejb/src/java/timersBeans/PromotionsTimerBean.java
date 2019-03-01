/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timersBeans;

import static java.lang.Math.log;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import session.PromotionsFacade;
import session.StatutpromotionsFacade;

/**
 *
 * @author steve
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class PromotionsTimerBean {

    @Inject
    PromotionsFacade promotions;
    
    @Inject 
    StatutpromotionsFacade statut;

    @Schedule(dayOfWeek = "*",hour="0")
    public void myTimer() {
        insert();
    }
    
    public void insert(){
        Random r = new Random();
        int rand = r.nextInt(3);
        int id = r.nextInt(3);
        String promo = "vide" ;
        if(rand==0)promo = "20 % sur tout le site ! A partir de 200€ d'achat";
        if(rand==1)promo ="Livraison offerte !";
        if(rand==2)promo ="10€ sur votre prochain achat ! A partir de 50€ d'achat";

        entity.Promotions prom = new entity.Promotions();
        prom.setStatut(statut.findAll().get(0));
        prom.setNb(1);
        prom.setNom(promo);
        prom.setCode("code");
        promotions.create(prom);
    }

    public int removePromo() {
        int rand = (int) (Math.random() * (3));
        promotions.remove(promotions.findAll().get(rand));
        return rand;
    }

    public String getRandomPromo() {
        int rand = (int) (Math.random() * (5));
        String promo ;
        switch (rand) {
            case 0:
                promo = "20 % sur tout le site ! A partir de 200€ d'achat";
                break;
            case 1:
                promo = "Livraison offerte !";
                break;
            case 2:
                promo = "10€ sur votre prochain achat ! A partir de 50€ d'achat";
                break;
            case 3:
                promo = "30% sur la catégorie Electroménager";
                break;
            case 4:
                promo = "5% de votre achat reversé à une association";
                break;
            default:promo="promo";break;

        }
        return promo;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
