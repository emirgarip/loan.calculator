package com.visma.loan.calculator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.visma.loan.calculator.exception.RestErrorHandler;
import com.visma.loan.calculator.model.LoanType;
import com.visma.loan.calculator.request.LoanRequest;
import com.visma.loan.calculator.response.LoanResponse;
import com.visma.loan.calculator.service.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(value = RestErrorHandler.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = LoanController.class)
class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LoanService loanService;

    @BeforeEach
    private void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new LoanController(loanService)).setControllerAdvice(new RestErrorHandler()).build();
    }

    @Test
    void testCalculateMonthlyPayment() throws Exception {
        LoanRequest loanRequest = new LoanRequest(LoanType.HOUSING_LOAN, 20, 200000);
        LoanResponse loanResponse = new LoanResponse(1159.92);

        when(loanService.calculatePayment(any())).thenReturn(loanResponse);

        mockMvc.perform(post("/loan/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.monthlyPayment").value(loanResponse.getMonthlyPayment()));
    }

    @ParameterizedTest
    @MethodSource("generateInvalidLoanRequests")
    void verifyErrorResponse(LoanRequest request, String message) throws Exception{
        mockMvc.perform(post("/loan/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").value(message));

    }

    private static List<Arguments> generateInvalidLoanRequests() {
        return List.of(
                Arguments.of(new LoanRequest(null, 10, 100000), "Loan type is mandatory"),
                Arguments.of(new LoanRequest(LoanType.HOUSING_LOAN, 0, 100000), "Term year must be between 1 - 30"),
                Arguments.of(new LoanRequest(LoanType.HOUSING_LOAN, 10, 0), "Amount value must be between 1 - 999999")
        );
    }
}

