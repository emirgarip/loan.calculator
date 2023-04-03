package com.visma.loan.calculator.strategy;

import com.visma.loan.calculator.model.Loan;
import com.visma.loan.calculator.model.LoanType;
import com.visma.loan.calculator.request.LoanRequest;
import org.springframework.stereotype.Component;

@Component
public class CarLoan extends Loan implements LoanStrategy {

    @Override
    public double calculateMonthlyPayment(LoanRequest loanRequest) {
        throw new UnsupportedOperationException("Not Implemented Yet");
    }

    @Override
    public LoanType getLoanStrategy() {
        return LoanType.CAR_LOAN;
    }
}
