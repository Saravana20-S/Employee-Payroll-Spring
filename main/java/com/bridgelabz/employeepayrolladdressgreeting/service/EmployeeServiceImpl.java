package com.bridgelabz.employeepayrolladdressgreeting.service;


import com.bridgelabz.employeepayrolladdressgreeting.exception.DepartmentNotFound;
import com.bridgelabz.employeepayrolladdressgreeting.exception.EmployeeNotFound;
import com.bridgelabz.employeepayrolladdressgreeting.dto.request.EmployeeRequestDTO;
import com.bridgelabz.employeepayrolladdressgreeting.dto.response.EmployeeResponseDTO;
import com.bridgelabz.employeepayrolladdressgreeting.model.Department;
import com.bridgelabz.employeepayrolladdressgreeting.model.Employee;
import com.bridgelabz.employeepayrolladdressgreeting.repository.DepartmentRepository;
import com.bridgelabz.employeepayrolladdressgreeting.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    //Add employee to the database
    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO requestDTO) throws DepartmentNotFound {
        Employee employee = employeeRepository.save(convertToEmployee( new Employee(),requestDTO));
        return convertToEmployeeResponseDTO(employee);
    }

    //get all emloyees from database
    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {

        List<EmployeeResponseDTO> employees = employeeRepository.findAll().stream()
                .map(this::convertToEmployeeResponseDTO)
                .toList();
        return employees;
    }

    //get employee by id
    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) throws EmployeeNotFound {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFound("Employee not found by id"));
        return convertToEmployeeResponseDTO(employee);
    }

    //update employee by id
    @Override
    public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO requestDTO) throws EmployeeNotFound, DepartmentNotFound {
        Employee oldEmployee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFound(("Employee not found by id")));
        Employee employee = convertToEmployee(oldEmployee, requestDTO);
       return convertToEmployeeResponseDTO( employeeRepository.save(employee));


    }

    //delete employee
    @Override
    public void deleteEmployee(Long id) throws EmployeeNotFound {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFound("Employee not found by id"));
        employeeRepository.delete(employee);
    }

    public Employee convertToEmployee(Employee employee,EmployeeRequestDTO requestDTO) throws DepartmentNotFound {
        employee.setName(requestDTO.getName());
        employee.setEmail(requestDTO.getEmail());
        employee.setSalary(requestDTO.getSalary());
        Department department = departmentRepository.findById(requestDTO.getDepartmentId()).orElseThrow(() -> new DepartmentNotFound("Department not found by id"));
        employee.setDepartment(department);
        return employee;

    }

    public EmployeeResponseDTO convertToEmployeeResponseDTO(Employee employee){
        EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
        responseDTO.setId(employee.getId());
        responseDTO.setName(employee.getName());
        responseDTO.setEmail(employee.getEmail());
        responseDTO.setSalary(employee.getSalary());
        responseDTO.setDepartmentId(employee.getDepartment().getId());
        return responseDTO;
    }
}
