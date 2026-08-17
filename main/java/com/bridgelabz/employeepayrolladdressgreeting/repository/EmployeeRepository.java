package com.bridgelabz.employeepayrolladdressgreeting.repository;

import com.bridgelabz.employeepayrolladdressgreeting.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
