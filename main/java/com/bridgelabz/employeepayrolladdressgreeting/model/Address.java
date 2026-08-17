package com.bridgelabz.employeepayrolladdressgreeting.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private String zipCode;

    @OneToOne(mappedBy = "address")
    private Employee employee;
}