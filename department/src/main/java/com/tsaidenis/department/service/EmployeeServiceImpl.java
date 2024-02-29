package com.tsaidenis.department.service;

import com.tsaidenis.department.controller.EmployeeFeign;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmployeeServiceImpl {
    private final EmployeeFeign employeeFeign;

    @CircuitBreaker(name = "getSumSalary",fallbackMethod = "fallbackGetSumSalary")
    public Double getSumSalary(Long id) {
        return employeeFeign.getSumSalDepartment(id);
    }

    public Double fallbackGetSumSalary(Long id) {
        return 15.00;
    }

    @CircuitBreaker(name = "getSumEmployees",fallbackMethod = "fallbackGetSumEmployees")
    public Integer getSumEmployees(Long id) {
        return employeeFeign.getSumEmployees(id);
    }

    public Integer fallbackGetSumEmployees(Long id) {
        return 1;
    }

    @CircuitBreaker(name = "getChefName",fallbackMethod = "fallbackGetChefName")
    public String getChefName(Long id) {
        return employeeFeign.getNameDepartmentChef(id);
    }

    public String fallbackGetChefName(Long id) {
        return "имя";
    }
}
