package org.adrian;

import javax.jms.JMSException;

public interface EventPublisher {
    /**
     * Publica un evento en un tópico específico.
     *
     * @param topic     El nombre del tópico al que se publicará el evento.
     * @param eventData Los datos del evento a publicar.
     * @throws JMSException Si ocurre un error al publicar el evento.
     */
    void publishEvent(String topic, String eventData) throws JMSException;
}