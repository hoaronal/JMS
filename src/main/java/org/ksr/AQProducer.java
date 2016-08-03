package org.ksr;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class AQProducer {

	public static void main(String[] args) throws JMSException {
		 ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://0.0.0.0:61616");
		 
         // Create a Connection
         Connection connection = connectionFactory.createConnection();
         connection.start();

         // Create a Session
         Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

         // Create the destination (Topic or Queue)
         Destination destination = session.createQueue("AP");

         // Create a MessageProducer from the Session to the Topic or Queue
         MessageProducer producer = session.createProducer(destination);
         producer.setDeliveryMode(DeliveryMode.PERSISTENT);
         for(int i=0;i<10;i++){
        	 System.out.println("Message sent to queue :"+"HI a"+i);
        	 /*TextMessage message = session.createTextMessage("HI a"+i);     */   
        	 
        	 ObjectMessage objectMessage = session.createObjectMessage();
        	 ClassA a=new ClassA();
        	 a.setA(i);
        	 objectMessage.setObject(a);
        	 producer.send(objectMessage);
         }
         // Clean up
         session.close();
         connection.close();
	}

}
