package com.bridgelabz.employeepayrolladdressgreeting.service;

import com.bridgelabz.employeepayrolladdressgreeting.exception.DepartmentNotFound;
import com.bridgelabz.employeepayrolladdressgreeting.exception.EmployeeNotFound;
import com.bridgelabz.employeepayrolladdressgreeting.dto.request.EmployeeRequestDTO;
import com.bridgelabz.employeepayrolladdressgreeting.dto.response.EmployeeResponseDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDTO createEmployee(EmployeeRequestDTO request) throws DepartmentNotFound;

    List<EmployeeResponseDTO> getAllEmployees();

    EmployeeResponseDTO getEmployeeById(Long id) throws EmployeeNotFound;

    EmployeeResponseDTO updateEmployee(
            Long id,
            EmployeeRequestDTO request) throws EmployeeNotFound, DepartmentNotFound;

    void deleteEmployee(Long id) throws EmployeeNotFound;
}