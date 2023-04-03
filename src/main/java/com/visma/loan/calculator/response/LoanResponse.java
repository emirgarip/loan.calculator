package com.visma.loan.calculator.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Digits;

@AllArgsConstructor
@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoanResponse {
    @Digits(integer = 10, fraction = 2)
    double monthlyPayment;
}
