package ru.netology.loanprocessing.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ru.netology.loanprocessing.event.LoanDecisionEvent;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class LoanDecisionPublisher {

    private static final String QUEUE = "loan-decision-queue";

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public LoanDecisionPublisher(
            RabbitTemplate rabbitTemplate,
            ObjectMapper objectMapper
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void publish(LoanDecisionEvent event) {
        try {
            String message = objectMapper.writeValueAsString(event);

            rabbitTemplate.convertAndSend(QUEUE, message);

            System.out.println(
                    "Decision sent to RabbitMQ: " + message
            );

        } catch (JsonProcessingException e) {
            throw new IllegalStateException(
                    "Cannot serialize decision event", e
            );
        }
    }
}
