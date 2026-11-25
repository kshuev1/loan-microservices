package ru.netology.loanapi.event;

import java.math.BigDecimal;

public record LoanApplicationEvent(
        Long applicationId,
        BigDecimal loanAmount,
        Integer termMonths,
        BigDecimal monthlyIncome,
        BigDecimal currentCreditLoad,
        Integer creditRating
) {
}