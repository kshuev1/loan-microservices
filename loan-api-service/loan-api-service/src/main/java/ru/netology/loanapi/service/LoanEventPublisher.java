package ru.netology.loanapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ru.netology.loanapi.event.LoanApplicationEvent;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LoanEventPublisher {

    private static final String TOPIC = "loan-applications";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public LoanEventPublisher(
            KafkaTemplate<String, String> kafkaTemplate,
            ObjectMapper objectMapper
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void publish(LoanApplicationEvent event) {
        try {
            String message = objectMapper.writeValueAsString(event);

            kafkaTemplate.send(TOPIC, event.applicationId().toString(), message)
                    .whenComplete((result, exception) -> {
                        if (exception != null) {
                            System.err.println(
                                    "Kafka send error: " + exception.getMessage()
                            );
                        }
                    });

        } catch (JsonProcessingException e) {
            throw new IllegalStateException(
                    "Cannot serialize loan event", e
            );
        }
    }
}
