package com.visma.loan.calculator.strategy;

import com.visma.loan.calculator.model.LoanType;
import com.visma.loan.calculator.request.LoanRequest;
import com.visma.loan.calculator.strategy.HousingLoan;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class HousingLoanTest {

    @InjectMocks
    HousingLoan housingLoan;

    @ParameterizedTest
    @MethodSource("generateLoanRequests")
    void testCalculatePayment(LoanRequest request, double message) {
        assertEquals(housingLoan.calculateMonthlyPayment(request), message);
    }

    private static List<Arguments> generateLoanRequests() {
        return List.of(
                Arguments.of(new LoanRequest(LoanType.HOUSING_LOAN, 20, 200000), 1159.92),
                Arguments.of(new LoanRequest(LoanType.HOUSING_LOAN, 10, 100000), 988.86)
        );
    }
}
