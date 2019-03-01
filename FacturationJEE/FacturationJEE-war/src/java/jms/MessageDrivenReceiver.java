package jms;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;

import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.TextMessage;

@JMSDestinationDefinition(name = "java:module/jms/myFacturationQueueRequest", interfaceName = "javax.jms.Queue", resourceAdapter = "jmsra", destinationName = "myFacturationQueueRequest")
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:module/jms/myFacturationQueueRequest")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MessageDrivenReceiver implements MessageListener {
    
    @Resource(mappedName = "java:module/jms/myFacturationQueueResponse")
    private Queue myFacturationQueue;
    
    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;
    
    
    @Override
    public void onMessage(Message message) {
        try {
            // Reception du message
            TextMessage textMessage = (TextMessage) message;
            String msgString = textMessage.getText();
            
            // Envoi de la réponse
            context.createProducer().send(myFacturationQueue, msgString);
            
            
        } catch (JMSException ex) {
            Logger.getLogger(MessageDrivenReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
}