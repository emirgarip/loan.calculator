package com.visma.loan.calculator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.visma.loan.calculator.component.LoanFactory;
import com.visma.loan.calculator.model.LoanType;
import com.visma.loan.calculator.request.LoanRequest;
import com.visma.loan.calculator.response.LoanResponse;
import com.visma.loan.calculator.strategy.LoanStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    private LoanFactory loanFactory;

    @InjectMocks
    private LoanService loanService;


    @Test
    void testCalculatePayment() {
        LoanRequest loanRequest = new LoanRequest(LoanType.HOUSING_LOAN, 20, 200000);
        LoanStrategy loanStrategy = mock(LoanStrategy.class);
        double expectedMonthlyPayment = 1159.92;

        when(loanFactory.findStrategy(LoanType.HOUSING_LOAN)).thenReturn(loanStrategy);
        when(loanStrategy.calculateMonthlyPayment(loanRequest)).thenReturn(expectedMonthlyPayment);

        LoanResponse actualResponse = loanService.calculatePayment(loanRequest);

        assertEquals(expectedMonthlyPayment, actualResponse.getMonthlyPayment());
    }
}

