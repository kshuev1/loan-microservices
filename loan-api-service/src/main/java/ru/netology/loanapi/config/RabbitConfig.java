package ru.netology.loanapi.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue loanDecisionQueue() {
        return new Queue("loan-decision-queue", true);
    }
}
