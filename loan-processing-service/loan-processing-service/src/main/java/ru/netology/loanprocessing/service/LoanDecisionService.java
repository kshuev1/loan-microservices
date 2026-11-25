package ru.netology.loanprocessing.service;

import ru.netology.loanprocessing.event.LoanApplicationEvent;
import ru.netology.loanprocessing.event.LoanDecisionEvent;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class LoanDecisionService {

    public LoanDecisionEvent process(
            LoanApplicationEvent event
    ) {
        BigDecimal monthlyPayment = event.loanAmount()
                .divide(
                        BigDecimal.valueOf(event.termMonths()),
                        2,
                        RoundingMode.HALF_UP
                );

        BigDecimal totalMonthlyLoad = monthlyPayment.add(
                event.currentCreditLoad()
        );

        BigDecimal maximumAllowedLoad = event.monthlyIncome()
                .multiply(new BigDecimal("0.50"));

        boolean approved = totalMonthlyLoad.compareTo(
                maximumAllowedLoad
        ) <= 0;

        String status = approved ? "APPROVED" : "REJECTED";

        return new LoanDecisionEvent(
                event.applicationId(),
                status
        );
    }
}
