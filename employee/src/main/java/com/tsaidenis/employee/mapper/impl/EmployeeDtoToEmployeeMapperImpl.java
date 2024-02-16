package com.tsaidenis.employee.mapper.impl;

import com.tsaidenis.employee.dto.EmployeeDto;
import com.tsaidenis.employee.mapper.EmployeeDtoToEmployeeMapper;
import com.tsaidenis.employee.model.Employee;
import com.tsaidenis.employee.model.Position;
import com.tsaidenis.employee.model.Sex;

public class EmployeeDtoToEmployeeMapperImpl implements EmployeeDtoToEmployeeMapper {
    @Override
    public Employee map(EmployeeDto dto) {

        return new Employee()
                .setName(dto.getName())
                .setSurname(dto.getSurname())
                .setPatronymic(dto.getPatronymic())
                .setDateBirth(dto.getDateBirth())
                .setSex(Sex.valueOf(dto.getSex()))
                .setPhone(dto.getPhone())
                .setEmail(dto.getEmail())
                .setDateOfFirstDay(dto.getDateOfFirstDay())
                .setPosition(Position.valueOf(dto.getPosition()))
                .setSalary(dto.getSalary())
                .setLeader(dto.getIsLeader())
                .setDeleted(dto.getDeleted())
                .setDepartmentId(dto.getDepartmentId());
    }
}
