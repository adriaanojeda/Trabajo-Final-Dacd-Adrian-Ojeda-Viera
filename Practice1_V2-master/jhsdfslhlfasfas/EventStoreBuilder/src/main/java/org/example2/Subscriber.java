package org.example2;

import javax.jms.JMSException;
import javax.jms.Message;

public interface Subscriber {
    void subscribeToTopic() throws JMSException;
    void onMessage(Message message);
}
