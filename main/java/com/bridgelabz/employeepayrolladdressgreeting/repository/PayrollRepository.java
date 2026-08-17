package com.bridgelabz.employeepayrolladdressgreeting.repository;

import com.bridgelabz.employeepayrolladdressgreeting.model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayrollRepository extends JpaRepository<Payroll,Long> {

    List<Payroll> findByEmployeeId(Long employeeId);

}
