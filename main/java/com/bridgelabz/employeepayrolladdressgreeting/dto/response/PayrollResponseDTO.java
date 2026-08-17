package com.bridgelabz.employeepayrolladdressgreeting.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class PayrollResponseDTO {

    private Long id;

    private BigDecimal basicSalary;

    private BigDecimal deductions;

    private BigDecimal netSalary;

    private Long employeeId;
}
