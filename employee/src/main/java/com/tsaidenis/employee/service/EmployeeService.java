package com.tsaidenis.employee.service;

import com.tsaidenis.employee.dto.EmployeeDto;
import com.tsaidenis.employee.model.Employee;

public interface EmployeeService {
    Employee create(EmployeeDto dto);
    Employee update(EmployeeDto dto, Long id);
}
