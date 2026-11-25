package ru.netology.loanapi.dto;

public record LoanApplicationResponse(
        Long applicationId,
        String status
) {
}
