package com.tsaidenis.employee.mapper;

import com.tsaidenis.employee.dto.EmployeeDto;
import com.tsaidenis.employee.model.Employee;

import java.util.List;

public interface EmployeeListToDtoListMapper
        extends Mapper<List<Employee>,List<EmployeeDto>>{
}
