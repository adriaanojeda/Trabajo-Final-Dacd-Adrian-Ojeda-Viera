package org.adrian;

import javax.jms.JMSException;

public interface Listener {
    void consume(String message) throws JMSException;
}
