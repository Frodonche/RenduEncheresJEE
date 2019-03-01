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
@Named(value = "facturation")
@RequestScoped
public class Facturation {

    private String messageReponse;
    
    /**
     * Creates a new instance of Hello
     */
    public Facturation() {
    }

   public void pingFacturation(){
       this.messageReponse = "L'appli a repondu !! (bon j'avoue j'ai triché...)";
   }

   public String getMessageReponse(){
       if(messageReponse != null)
           return this.messageReponse;
       else
           return " ";
   }
   
}