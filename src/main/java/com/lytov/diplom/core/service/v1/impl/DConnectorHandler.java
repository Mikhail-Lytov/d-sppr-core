package com.lytov.diplom.core.service.v1.impl;

import com.lytov.diplom.core.configuration.rabbit.DConnectorRMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class DConnectorHandler {


    /*@RabbitListener(queues = DConnectorRMQConfig.FROM_D_CONNECTOR_VULNERABILITY_QUEUE)
    public void handleConnector(
            @Payload
    ) {

    }*/
}
