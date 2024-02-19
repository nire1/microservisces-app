package com.tsaidenis.employee.controller;

import com.tsaidenis.employee.dto.EmployeeDto;
import com.tsaidenis.employee.dto.SumDto;
import com.tsaidenis.employee.model.Employee;
import com.tsaidenis.employee.service.EmployeeService;
import com.tsaidenis.employee.service.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping
@RestController
public class EmployeeControllerImpl implements EmployeeController{
    private final EmployeeServiceImpl employeeService;

    public EmployeeControllerImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }


    @Override
    public Employee create(EmployeeDto dto) {
        return employeeService.create(dto);
    }

    @Override
    public List<Employee> get() {
        return employeeService.get();
    }

    @Override
    public Employee get(long id) {
        return employeeService.get(id);
    }

    @Override
    public Employee update(EmployeeDto dto, Long id) {
        return employeeService.update(dto,id);
    }

    @Override
    public void leave(long id) {

        employeeService.leave(id);
    }

    @Override
    public Employee updateDepartment(long fromId, long toId) {
        return employeeService.moveEmployee(fromId,toId);
    }

    @Override
    public List<Employee> updateDepartments(long fromId, long toId) {
        return employeeService.moveAllEmployees(fromId,toId);
    }

    @Override
    public void delete(long id) {

        employeeService.delete(id);
    }

    @Override
    public Employee getLeader(long id) {
        return employeeService.getLeader(id);
    }

    @Override
    public Employee getByName(String name) {
        return employeeService.getByName(name);
    }

    @Override
    public List<Employee> getEmployeesByDepartment(Long id) {
        return employeeService.getEmpDep(id);
    }

    @Override
    public SumDto getSumSalary(Long id) {
        return employeeService.getSumDep(id);
    }

    @Override
    public Double getSumSalDepartment(Long id) {
        return employeeService.getSumDepartment(id);
    }

    @Override
    public Integer getSumEmployees(Long id) {
        return employeeService.getSumEmployees(id);
    }

    @Override
    public String getNameDepartmentChef(Long id) {
        return employeeService.getNameDepartmentChef(id);
    }
}
