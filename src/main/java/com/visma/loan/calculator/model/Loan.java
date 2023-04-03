package com.visma.loan.calculator.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.text.DecimalFormat;
import java.text.NumberFormat;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Loan {

    double interestRate;

    public double formatResult(double result) {
        NumberFormat formatter = new DecimalFormat("##.00");
        return Double.parseDouble(formatter.format(result));
    }

}
