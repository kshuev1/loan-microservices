package ru.netology.loanprocessing.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import ru.netology.loanprocessing.event.LoanApplicationEvent;
import ru.netology.loanprocessing.event.LoanDecisionEvent;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class LoanApplicationListener {

    private final ObjectMapper objectMapper;
    private final LoanDecisionService decisionService;
    private final LoanDecisionPublisher decisionPublisher;

    public LoanApplicationListener(
            ObjectMapper objectMapper,
            LoanDecisionService decisionService,
            LoanDecisionPublisher decisionPublisher
    ) {
        this.objectMapper = objectMapper;
        this.decisionService = decisionService;
        this.decisionPublisher = decisionPublisher;
    }

    @KafkaListener(
    topics = "loan-applications",
    groupId = "loan-processing-group"
    )
    public void handleApplication(String message) {
        try {
            LoanApplicationEvent event = objectMapper.readValue(
                    message,
                    LoanApplicationEvent.class
            );

            System.out.println(
                    "Received application: " + event.applicationId()
            );

            LoanDecisionEvent decision = decisionService.process(event);

            decisionPublisher.publish(decision);

        } catch (Exception e) {
            throw new IllegalStateException(
                    "Cannot process loan application", e
            );
        }
    }
}
