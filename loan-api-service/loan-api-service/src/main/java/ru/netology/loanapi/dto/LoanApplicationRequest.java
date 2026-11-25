package ru.netology.loanapi.dto;

import java.math.BigDecimal;

public record LoanApplicationRequest(
        BigDecimal loanAmount,
        Integer termMonths,
        BigDecimal monthlyIncome,
        BigDecimal currentCreditLoad,
        Integer creditRating
) {
}
