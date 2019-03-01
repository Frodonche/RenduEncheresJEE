package jms;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSRuntimeException;
import javax.jms.Queue;



/**
 *
 * @author guigu
 */

@Named(value = "receiverBean")
@RequestScoped
public class ReceiverBean {

    /**
     * Creates a new instance of SenderBean
     */
    @Inject
    private JMSContext context;
    
    @Resource(lookup = "java:module/jms/myFacturationQueueResponse")
    private Queue queueFacturation;
    
    @Resource(lookup = "java:module/jms/myLivraisonQueueResponse")
    private Queue queueLivraison;
    
    
    public ReceiverBean() {
    }
    
    public String getMessageFacturation() {
        try {
            JMSConsumer receiver = context.createConsumer(queueFacturation);
            
            // le texte recupere correspond à l'id de l'article
            String text = receiver.receiveBody(String.class, 1000);

            if (text != null) {
                FacesMessage facesMessage =
                        new FacesMessage("Reading message: " + text);
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                
                // Faire l'insertion dans la bdd ici
                
                return text;
            } else {
                FacesMessage facesMessage =
                        new FacesMessage("No message received after 1 second");
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                return " ";
            }
        } catch (JMSRuntimeException t) {
                    System.out.println(t.toString());
        }
        return "error";
    }
    
    public String getMessageLivraison() {
        try {
            JMSConsumer receiver = context.createConsumer(queueLivraison);
            
            // le texte recupere correspond à l'id de l'article
            String text = receiver.receiveBody(String.class, 1000);

            if (text != null) {
                FacesMessage facesMessage =
                        new FacesMessage("Reading message: " + text);
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                
                // Faire l'insertion dans la bdd ici
                
                return text;
            } else {
                FacesMessage facesMessage =
                        new FacesMessage("No message received after 1 second");
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                return " ";
            }
        } catch (JMSRuntimeException t) {
                    System.out.println(t.toString());
        }
        return "error";
    }
    
}
