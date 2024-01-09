package org.adrian;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ActiveMQEventPublisher implements EventPublisher {
    private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    @Override
    public void publishEvent(String topic, String eventData) throws JMSException {
        try (Connection connection = new ActiveMQConnectionFactory(BROKER_URL).createConnection()) {
            connection.start();

            try (Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)) {
                Topic destination = session.createTopic(topic);

                try (MessageProducer producer = session.createProducer(destination)) {
                    TextMessage message = session.createTextMessage(eventData);
                    producer.send(message);
                    System.out.println("Published event to topic: " + topic);
                }
            }
        } catch (JMSException e) {
            // Log or handle the exception as needed
            e.printStackTrace();
            throw e;
        }
    }
}