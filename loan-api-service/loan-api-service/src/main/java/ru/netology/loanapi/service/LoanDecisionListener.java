package ru.netology.loanapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import ru.netology.loanapi.event.LoanDecisionEvent;
import ru.netology.loanapi.repository.LoanApplicationRepository;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LoanDecisionListener {

    private final LoanApplicationRepository repository;
    private final ObjectMapper objectMapper;

    public LoanDecisionListener(
            LoanApplicationRepository repository,
            ObjectMapper objectMapper
    ) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "loan-decision-queue")
    @Transactional
    public void handleDecision(String message) {
        try {
            LoanDecisionEvent event = objectMapper.readValue(
                    message,
                    LoanDecisionEvent.class
            );

            repository.findById(event.applicationId())
                    .ifPresent(application -> {
                        application.setStatus(event.status());
                        repository.save(application);
                    });

            System.out.println(
                    "Application " + event.applicationId()
                            + " status updated to " + event.status()
            );

        } catch (Exception e) {
            throw new IllegalStateException(
                    "Cannot process loan decision event", e
            );
        }
    }
}
