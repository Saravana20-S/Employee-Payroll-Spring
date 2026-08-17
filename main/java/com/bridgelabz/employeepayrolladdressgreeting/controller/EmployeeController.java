package com.bridgelabz.employeepayrolladdressgreeting.controller;

import com.bridgelabz.employeepayrolladdressgreeting.exception.DepartmentNotFound;
import com.bridgelabz.employeepayrolladdressgreeting.exception.EmployeeNotFound;
import com.bridgelabz.employeepayrolladdressgreeting.dto.request.EmployeeRequestDTO;
import com.bridgelabz.employeepayrolladdressgreeting.dto.response.EmployeeResponseDTO;
import com.bridgelabz.employeepayrolladdressgreeting.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    // Create a new employee
    @PostMapping
    public EmployeeResponseDTO addEmployee(@Valid @RequestBody EmployeeRequestDTO requestDTO) throws DepartmentNotFound {
        return employeeService.createEmployee(requestDTO);
    }

    // Retrieve all employees
    @GetMapping
    public List<EmployeeResponseDTO> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    // Retrieve an employee by ID
    @GetMapping("/{id}")
    public EmployeeResponseDTO getEmployeeById(@PathVariable Long id) throws EmployeeNotFound {
        return employeeService.getEmployeeById(id);
    }

    // Update an existing employee by ID
    @PutMapping("/{id}")
    public EmployeeResponseDTO updateEmployee(@PathVariable Long id,@RequestBody EmployeeRequestDTO requestDTO) throws EmployeeNotFound, DepartmentNotFound {
        return employeeService.updateEmployee(id,requestDTO);
    }

    // Delete an employee by ID
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) throws EmployeeNotFound {
        employeeService.deleteEmployee(id);
        return "Employee deleted successfully";
    }
}
