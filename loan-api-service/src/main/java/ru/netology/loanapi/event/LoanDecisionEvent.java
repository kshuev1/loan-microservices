package ru.netology.loanapi.event;

public record LoanDecisionEvent(
        Long applicationId,
        String status
) {
}
