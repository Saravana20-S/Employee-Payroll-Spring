package com.bridgelabz.employeepayrolladdressgreeting.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal deductions;

    private BigDecimal netSalary;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}