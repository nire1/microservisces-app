package com.tsaidenis.employee.mapper.impl;

import com.tsaidenis.employee.dto.EmployeeDto;
import com.tsaidenis.employee.mapper.EmployeeListToDtoListMapper;
import com.tsaidenis.employee.model.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class EmployeeListToDtoListMapperImpl implements EmployeeListToDtoListMapper {
    @Override
    public List<Employee> map(List<EmployeeDto> source) {
        return null;
    }

}
