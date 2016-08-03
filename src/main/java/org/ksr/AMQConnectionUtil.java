package org.ksr;

import javax.jms.Connection;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;

public class AMQConnectionUtil {

	private static Connection connection;

	public static void getInstance() throws JMSException{
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://0.0.0.0:61616");		 
         // Create a Connection
        connection = connectionFactory.createConnection();
        connection.start();
	}
}
