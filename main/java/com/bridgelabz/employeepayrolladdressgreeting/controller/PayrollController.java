package com.bridgelabz.employeepayrolladdressgreeting.controller;

import com.bridgelabz.employeepayrolladdressgreeting.exception.EmployeeNotFound;
import com.bridgelabz.employeepayrolladdressgreeting.dto.request.PayrollRequestDTO;
import com.bridgelabz.employeepayrolladdressgreeting.dto.response.PayrollResponseDTO;
import com.bridgelabz.employeepayrolladdressgreeting.service.PayrollService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees/{id}/payroll")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    @PostMapping
    public PayrollResponseDTO createPayroll(@PathVariable Long id, @Valid @RequestBody PayrollRequestDTO requestDTO) throws EmployeeNotFound {

        return payrollService.createPayroll(id, requestDTO);

    }

    @GetMapping
    public List<PayrollResponseDTO> getEmployeePayroll(@PathVariable Long id) throws EmployeeNotFound {

        return payrollService.getEmployeePayroll(id);


    }
}