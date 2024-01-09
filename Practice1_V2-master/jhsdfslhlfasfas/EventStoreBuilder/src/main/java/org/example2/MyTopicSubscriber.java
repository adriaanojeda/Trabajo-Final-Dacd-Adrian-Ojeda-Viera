package org.example2;

import org.apache.activemq.jms.pool.PooledConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

public class MyTopicSubscriber {
    public static void main(String[] args) throws JMSException {
        // Configura la conexión
        PooledConnectionFactory factory = new PooledConnectionFactory();
        factory.setBlockIfSessionPoolIsFull(Boolean.parseBoolean("tcp://localhost:61616"));

        Connection connection = factory.createConnection();
        connection.start();

        // Crea una sesión
        TopicConnection topicConnection = (TopicConnection) connection;
        TopicSession session = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        // Obtén el objeto de tema
        Topic topic = session.createTopic("tu_tema"); // Reemplaza con el nombre de tu tema

        // Crea un suscriptor de tema
        TopicSubscriber subscriber = session.createSubscriber(topic);

        // Haz algo con el suscriptor, como recibir mensajes

        // Cierra la conexión
        connection.close();
    }
}
