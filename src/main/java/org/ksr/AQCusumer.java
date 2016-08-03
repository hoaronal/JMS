package org.ksr;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class AQCusumer implements MessageListener {

	public static void main(String[] args) throws JMSException {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://0.0.0.0:61616");
		 
        // Create a Connection
        Connection connection = connectionFactory.createConnection();
        connection.start();
        // Create a Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
        // Create the destination (Topic or Queue)
        Destination destination = session.createQueue("AP");

        // Create a MessageConsumer from the Session to the Topic or Queue
        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(new AQCusumer());
	}

	public void onMessage(Message message) {
		if(message instanceof ObjectMessage){
			try {				
				ObjectMessage object=(ObjectMessage)message;
				ClassA a=(ClassA)object.getObject();
				System.out.println(a.getA());				
			} catch (JMSException e) {				
				e.printStackTrace();
			}	
		}
	}

}
