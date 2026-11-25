package ru.netology.loanapi.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "loan_applications")
public class LoanApplication {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal loanAmount;

    @Column(nullable = false)
    private Integer termMonths;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal monthlyIncome;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal currentCreditLoad;

    @Column(nullable = false)
    private Integer creditRating;

    @Column(nullable = false)
    private String status = "PENDING";

    public LoanApplication() {
    }

    public LoanApplication(
            BigDecimal loanAmount,
            Integer termMonths,
            BigDecimal monthlyIncome,
            BigDecimal currentCreditLoad,
            Integer creditRating
    ) {
        this.loanAmount = loanAmount;
        this.termMonths = termMonths;
        this.monthlyIncome = monthlyIncome;
        this.currentCreditLoad = currentCreditLoad;
        this.creditRating = creditRating;
        this.status = "PENDING";
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public Integer getTermMonths() {
        return termMonths;
    }

    public BigDecimal getMonthlyIncome() {
        return monthlyIncome;
    }

    public BigDecimal getCurrentCreditLoad() {
        return currentCreditLoad;
    }

    public Integer getCreditRating() {
        return creditRating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
