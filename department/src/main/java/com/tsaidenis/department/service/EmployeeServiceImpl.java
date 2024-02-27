package com.tsaidenis.department.service;

import com.tsaidenis.department.controller.EmployeeFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmployeeServiceImpl {
    EmployeeFeign employeeFeign;


    public Double getSumSalary(Long id) {
        return employeeFeign.getSumSalDepartment(id);
    }

    public Double fallbackGetSumSalary(Long id) {
        return 15.00;
    }


    public Integer getSumEmployees(Long id) {
        return employeeFeign.getSumEmployees(id);
    }

    public Integer fallbackGetSumEmployees(Long id) {
        return 1;
    }


    public String getChefName(Long id) {
        return employeeFeign.getNameDepartmentChef(id);
    }

    public String fallbackGetChefName(Long id) {
        return "имя";
    }
}
