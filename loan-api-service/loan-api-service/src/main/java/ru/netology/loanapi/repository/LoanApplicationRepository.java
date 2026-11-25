package ru.netology.loanapi.repository;

import ru.netology.loanapi.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanApplicationRepository
        extends JpaRepository<LoanApplication, Long> {
}
