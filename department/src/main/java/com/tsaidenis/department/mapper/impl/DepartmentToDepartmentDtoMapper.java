package com.tsaidenis.department.mapper.impl;

import com.tsaidenis.department.dto.DepartmentDto;
import com.tsaidenis.department.model.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentToDepartmentDtoMapper implements com.tsaidenis.department.mapper.DepartmentToDepartmentDtoMapper {
    @Override
    public DepartmentDto map(Department department) {
        return new DepartmentDto()
                .setId(department.getId())
                .setName(department.getName())
                .setCreateDate(department.getCreateDate())
                .setUpperDepartment(department.getUpperDepartment());
    }
}
