package com.tsaidenis.department.service;

import com.tsaidenis.department.controller.EmployeeFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmployeeServiceImpl {
    EmployeeFeign employeeFeign;

    public double getSumSalary(Long id) {
        return employeeFeign.getSumSalDepartment(id);
    }
}
