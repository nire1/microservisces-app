package com.tsaidenis.employee.controller;

import com.tsaidenis.employee.dto.EmployeeDto;
import com.tsaidenis.employee.model.Employee;
import com.tsaidenis.employee.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping
@RestController
public class EmployeeControllerImpl implements EmployeeController{
    @Override
    public EmployeeDto create(EmployeeDto dto) {
        return null;
    }

    @Override
    public List<Employee> get() {
        return null;
    }

    @Override
    public Employee get(long id) {
        return null;
    }

    @Override
    public Employee update(EmployeeDto dto, Long id) {
        return null;
    }

    @Override
    public void leave(long id) {

    }

    @Override
    public Employee updateDepartment(long fromId, long toId) {
        return null;
    }

    @Override
    public List<Employee> updateDepartments(long fromId, long toId) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Employee getLeader(long id) {
        return null;
    }

    @Override
    public Employee getByName(long id, String name) {
        return null;
    }

    @Override
    public List<EmployeeDto> getEmployeesByDepartment(Long id) {
        return null;
    }
}
