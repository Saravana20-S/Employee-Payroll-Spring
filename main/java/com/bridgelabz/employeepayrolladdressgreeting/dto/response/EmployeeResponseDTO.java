package com.bridgelabz.employeepayrolladdressgreeting.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class EmployeeResponseDTO {

    private Long id;

    private String name;

    private String email;

    private BigDecimal salary;

    private Long departmentId;
}