package com.bridgelabz.employeepayrolladdressgreeting.service;

import com.bridgelabz.employeepayrolladdressgreeting.exception.EmployeeNotFound;
import com.bridgelabz.employeepayrolladdressgreeting.dto.request.PayrollRequestDTO;
import com.bridgelabz.employeepayrolladdressgreeting.dto.response.PayrollResponseDTO;

import java.util.List;

public interface PayrollService {

    PayrollResponseDTO createPayroll(Long employeeId, PayrollRequestDTO requestDTO) throws EmployeeNotFound;

    List<PayrollResponseDTO> getEmployeePayroll(Long employeeId) throws EmployeeNotFound;
}