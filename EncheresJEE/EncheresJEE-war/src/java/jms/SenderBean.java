package jms;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 *
 * @author guigu
 */
@Named(value = "senderBean")
@RequestScoped
public class SenderBean {

    @Resource(mappedName = "java:module/jms/myFacturationQueueRequest")
    private Queue myFacturationQueue;
    
    @Resource(mappedName = "java:module/jms/myLivraisonQueueRequest")
    private Queue myLivraisonQueue;

    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;

    private String messageTextFacturation;
    private String messageTextLivraison;
    
    /**
     * Creates a new instance of SenderBean
     */
    public SenderBean() {
    }

    public String getMessageTextFacturation() {
        return messageTextFacturation;
    }

    public void setMessageTextFacturation(String messageText) {
        this.messageTextFacturation = messageText;
    }
    
    public String getMessageTextLivraison() {
        return messageTextLivraison;
    }

    public void setMessageTextLivraison(String messageText) {
        this.messageTextLivraison = messageText;
    }

    public void sendMsgToFacturationQueue(String messageTextFacturation) {
        this.messageTextFacturation = messageTextFacturation;
        context.createProducer().send(myFacturationQueue, messageTextFacturation);
    }
    
    public void sendMsgToLivraisonQueue(String messageTextLivraison) {
        this.messageTextLivraison = messageTextLivraison;
        context.createProducer().send(myLivraisonQueue, messageTextLivraison);
    }
    
}
