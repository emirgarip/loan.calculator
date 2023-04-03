package com.visma.loan.calculator.service;

import com.visma.loan.calculator.component.LoanFactory;
import com.visma.loan.calculator.request.LoanRequest;
import com.visma.loan.calculator.response.LoanResponse;
import com.visma.loan.calculator.strategy.LoanStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoanService {

    private final LoanFactory loanFactory;

    public LoanResponse calculatePayment(LoanRequest request){
        LoanStrategy strategy =
                loanFactory.findStrategy(request.getType());
        double monthlyPayment = strategy.calculateMonthlyPayment(request);
        return LoanResponse.builder().monthlyPayment(monthlyPayment).build();
    }
}
