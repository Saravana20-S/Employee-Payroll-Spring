package com.bridgelabz.employeepayrolladdressgreeting.model;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private BigDecimal salary;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

    @OneToOne
    @JoinColumn(name = "address_id",unique = true)
    private Address address;

    @OneToMany(mappedBy = "employee")
    private List<Payroll> payrolls=new ArrayList<>();
}
