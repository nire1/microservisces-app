package com.tsaidenis.employee.service;

import com.tsaidenis.employee.dto.EmployeeDto;
import com.tsaidenis.employee.model.Employee;
import com.tsaidenis.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl {
    private final EmployeeRepository employeeRepository;

    private final MapperFacade mapperFacade;


    public Employee from(EmployeeDto dto){
        return mapperFacade.map(dto,Employee.class);
    }
    public Employee create(EmployeeDto dto) {

        Employee employee = from(dto);
        employee.setDepartmentId(dto.getDepartmentId());
        return employeeRepository.save(employee);
    }

}
