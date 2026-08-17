package com.bridgelabz.employeepayrolladdressgreeting.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class EmployeeRequestDTO {
    @NotBlank(message = "Name cannote be blank")
    private String name;

    @NotBlank(message = "Email cannote be blank")
    @Email
    private String email;

    @Positive(message = "Salary must be greater than 0")
    @NotNull(message = "Salary is required")
    private BigDecimal salary;

    @NotNull(message = "departmentId is required")
    private Long departmentId;
}