package ru.netology.loanprocessing.event;

public record LoanDecisionEvent(
        Long applicationId,
        String status
) {
}
