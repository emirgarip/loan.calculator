package com.visma.loan.calculator.strategy;

import com.visma.loan.calculator.model.Loan;
import com.visma.loan.calculator.model.LoanType;
import com.visma.loan.calculator.request.LoanRequest;
import org.springframework.stereotype.Component;

@Component
public class HousingLoan extends Loan implements LoanStrategy {

    public HousingLoan() {
        this.setInterestRate(0.035);
    }

    @Override
    public double calculateMonthlyPayment(LoanRequest loanRequest) {
        double monthlyInterestRate = getInterestRate() / 12;
        int numberOfPayments = loanRequest.getTerm() * 12;
        double result = (loanRequest.getAmount() * monthlyInterestRate) /
                (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));
        return formatResult(result);
    }

    @Override
    public LoanType getLoanStrategy() {
        return LoanType.HOUSING_LOAN;
    }
}
