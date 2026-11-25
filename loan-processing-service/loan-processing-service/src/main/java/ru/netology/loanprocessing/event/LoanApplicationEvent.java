package ru.netology.loanprocessing.event;

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
