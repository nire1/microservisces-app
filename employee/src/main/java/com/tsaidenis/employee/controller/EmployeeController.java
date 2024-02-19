package com.tsaidenis.employee.controller;

import com.tsaidenis.employee.dto.EmployeeDto;
import com.tsaidenis.employee.dto.SumDto;
import com.tsaidenis.employee.model.Employee;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface EmployeeController {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/employees", consumes = MediaType.APPLICATION_JSON_VALUE)
    Employee create(@RequestBody @Valid EmployeeDto dto);
    @GetMapping("/employees")
    List<Employee> get();
    @GetMapping("/employees/{id}")
    Employee get(@PathVariable("id") long id);

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/employees/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Employee update(@RequestBody @Valid EmployeeDto dto, @PathVariable("id") Long id);

    @PutMapping(value = "/employees/{id}/leave", consumes = MediaType.APPLICATION_JSON_VALUE)
    void leave(@PathVariable("id") long id);

    @PutMapping(value = "/employees/{from}/department/{to}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Employee updateDepartment(@PathVariable("from") long fromId,@PathVariable("to") long toId);

    @PutMapping(value = "/employees/{from}/departmentall/{to}", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Employee> updateDepartments(@PathVariable("from") long fromId, @PathVariable("to") long toId);
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/employees/{id}")
    void delete(@PathVariable("id") long id);

    @GetMapping("/employees/{id}/chef")
    Employee getLeader(@PathVariable("id") long id);

    @GetMapping("/employees/name")
    Employee getByName(@PathParam("name") String name);

    @GetMapping("/employees/{id}/department")
    List<Employee> getEmployeesByDepartment(@PathVariable("id") Long id);

    @GetMapping("/employees/{id}/sum")
    SumDto getSumSalary(@PathVariable("id") Long id);

    @GetMapping("/{id}/salary")
    Double getSumSalDepartment(@PathVariable("id") Long id);

    @GetMapping("/{id}/employee/count")
    Integer getSumEmployees(@PathVariable("id") Long id);

    @GetMapping("/{id}/namechef")
    String getNameDepartmentChef(@PathVariable("id") Long id);

}
