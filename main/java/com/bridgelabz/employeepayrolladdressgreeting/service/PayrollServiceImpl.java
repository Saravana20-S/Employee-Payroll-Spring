package com.bridgelabz.employeepayrolladdressgreeting.service;

import com.bridgelabz.employeepayrolladdressgreeting.exception.EmployeeNotFound;
import com.bridgelabz.employeepayrolladdressgreeting.dto.request.PayrollRequestDTO;
import com.bridgelabz.employeepayrolladdressgreeting.dto.response.PayrollResponseDTO;
import com.bridgelabz.employeepayrolladdressgreeting.model.Employee;
import com.bridgelabz.employeepayrolladdressgreeting.model.Payroll;
import com.bridgelabz.employeepayrolladdressgreeting.repository.EmployeeRepository;
import com.bridgelabz.employeepayrolladdressgreeting.repository.PayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PayrollServiceImpl implements PayrollService {

    @Autowired
    private PayrollRepository payrollRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public PayrollResponseDTO createPayroll(Long employeeId, PayrollRequestDTO requestDTO) throws EmployeeNotFound {

        // 1. Find employee
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFound("Employee not found by id"));

        // 2. Get salary from employee
        BigDecimal salary = employee.getSalary();

        // 3. Get deductions from request
        BigDecimal deductions = requestDTO.getDeductions();

        // 4. Calculate net salary
        BigDecimal netSalary = salary.subtract(deductions);

        // 5. Create Payroll object
        Payroll payroll = new Payroll();
        payroll.setDeductions(deductions);
        payroll.setNetSalary(netSalary);
        payroll.setEmployee(employee);

        // 6. Save payroll
        Payroll savedPayroll = payrollRepository.save(payroll);

        // 7. Convert to response DTO
        return convertToPayrollResponseDTO(savedPayroll);
    }

    @Override
    public List<PayrollResponseDTO> getEmployeePayroll(Long employeeId) throws EmployeeNotFound {

        // Check whether employee exists
        employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFound("Employee not found by id"));

        // Get payroll records
        List<Payroll> payrolls = payrollRepository.findByEmployeeId(employeeId);

        // Convert entities to DTOs
        return payrolls.stream()
                .map(this::convertToPayrollResponseDTO)
                .toList();
    }

    private PayrollResponseDTO convertToPayrollResponseDTO(Payroll payroll) {

        PayrollResponseDTO responseDTO = new PayrollResponseDTO();

        responseDTO.setId(payroll.getId());

        responseDTO.setBasicSalary(payroll.getEmployee().getSalary());

        responseDTO.setDeductions(payroll.getDeductions());

        responseDTO.setNetSalary(payroll.getNetSalary());

        responseDTO.setEmployeeId(payroll.getEmployee().getId());

        return responseDTO;
    }
}