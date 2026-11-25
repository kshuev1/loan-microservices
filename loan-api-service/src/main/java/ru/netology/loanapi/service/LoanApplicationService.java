package ru.netology.loanapi.service;

import ru.netology.loanapi.dto.LoanApplicationRequest;
import ru.netology.loanapi.dto.LoanApplicationResponse;
import ru.netology.loanapi.entity.LoanApplication;
import ru.netology.loanapi.event.LoanApplicationEvent;
import ru.netology.loanapi.repository.LoanApplicationRepository;

import org.springframework.stereotype.Service;


@Service
public class LoanApplicationService {

    private final LoanApplicationRepository repository;
    private final LoanEventPublisher eventPublisher;

    public LoanApplicationService(
            LoanApplicationRepository repository,
            LoanEventPublisher eventPublisher
    ) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }


    public LoanApplicationResponse create(
            LoanApplicationRequest request
    ) {
        LoanApplication application = new LoanApplication(
                request.loanAmount(),
                request.termMonths(),
                request.monthlyIncome(),
                request.currentCreditLoad(),
                request.creditRating()
        );

        LoanApplication saved = repository.saveAndFlush(application);

        LoanApplicationEvent event = new LoanApplicationEvent(
                saved.getId(),
                saved.getLoanAmount(),
                saved.getTermMonths(),
                saved.getMonthlyIncome(),
                saved.getCurrentCreditLoad(),
                saved.getCreditRating()
        );

        eventPublisher.publish(event);

        return new LoanApplicationResponse(
                saved.getId(),
                saved.getStatus()
        );
    }

    public LoanApplicationResponse getStatus(Long id) {
        LoanApplication application = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Loan application not found: " + id
                ));

        return new LoanApplicationResponse(
                application.getId(),
                application.getStatus()
        );
    }
}
