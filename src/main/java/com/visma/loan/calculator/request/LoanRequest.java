package com.visma.loan.calculator.request;


import com.visma.loan.calculator.model.LoanType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoanRequest {
    @NotNull(message = "Loan type is mandatory")
    LoanType type;
    @Range(min = 1, max =30, message = "Term year must be between {min} - {max}")
    int term;
    @Range(min = 1, max =999999, message = "Amount value must be between {min} - {max}")
    int amount;
}
