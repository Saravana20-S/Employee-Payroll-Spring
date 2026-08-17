package com.bridgelabz.employeepayrolladdressgreeting.dto.request;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
public class PayrollRequestDTO {
    @PositiveOrZero
    private BigDecimal deductions;
}