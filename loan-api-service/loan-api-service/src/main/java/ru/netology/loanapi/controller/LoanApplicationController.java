package ru.netology.loanapi.controller;

import ru.netology.loanapi.dto.LoanApplicationRequest;
import ru.netology.loanapi.dto.LoanApplicationResponse;
import ru.netology.loanapi.service.LoanApplicationService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
public class LoanApplicationController {

    private final LoanApplicationService service;

    public LoanApplicationController(
            LoanApplicationService service
    ) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoanApplicationResponse create(
            @RequestBody LoanApplicationRequest request
    ) {
        return service.create(request);
    }

    @GetMapping("/{id}")
    public LoanApplicationResponse getStatus(
            @PathVariable Long id
    ) {
        return service.getStatus(id);
    }
}
