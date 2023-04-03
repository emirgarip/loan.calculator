package com.visma.loan.calculator.controller;

import com.visma.loan.calculator.request.LoanRequest;
import com.visma.loan.calculator.response.LoanResponse;
import com.visma.loan.calculator.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/loan")
@RestController
public class LoanController {

    private final LoanService loanService;

    @PostMapping("/calculate")
    public LoanResponse calculateMonthlyPayment(@RequestBody @Valid LoanRequest request) {
        return loanService.calculatePayment(request);
    }

}
