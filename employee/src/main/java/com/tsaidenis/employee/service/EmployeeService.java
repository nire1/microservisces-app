package com.tsaidenis.employee.service;

import com.tsaidenis.employee.dto.EmployeeDto;
import com.tsaidenis.employee.dto.SumDto;
import com.tsaidenis.employee.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee create(EmployeeDto employeeDto);

    List<Employee> get();

    Employee update(EmployeeDto dto,long id);

    void leave(long id);

    Employee get(long id);

    Employee moveEmployee(long fromId,long dep);
    List<Employee> getEmpDep(Long id);

    List<Employee> moveAllEmployees(long fromId,long toId);

    Employee getLeader(long id);

    Employee getByName(String name);
    void delete(Long id);
    boolean validationSalary(EmployeeDto dto);
    SumDto getSumDep(long id);
}

