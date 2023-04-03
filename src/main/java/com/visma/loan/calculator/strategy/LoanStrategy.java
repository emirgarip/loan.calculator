package com.visma.loan.calculator.strategy;

import com.visma.loan.calculator.model.LoanType;
import com.visma.loan.calculator.request.LoanRequest;

public interface LoanStrategy {
    double calculateMonthlyPayment(LoanRequest loanRequest);
    LoanType getLoanStrategy();
}
