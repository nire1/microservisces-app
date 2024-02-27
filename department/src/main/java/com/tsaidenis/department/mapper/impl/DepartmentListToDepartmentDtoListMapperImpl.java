package com.tsaidenis.department.mapper.impl;

import com.tsaidenis.department.dto.DepartmentDto;
import com.tsaidenis.department.mapper.DepartmentListToDepartmentDtoListMapper;
import com.tsaidenis.department.model.Department;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentListToDepartmentDtoListMapperImpl implements DepartmentListToDepartmentDtoListMapper {
    @Override
    public List<DepartmentDto> map(List<Department> departmentList) {
        List<DepartmentDto>dtoList = new ArrayList<>();
        for(Department department:departmentList){
            DepartmentDto departmentDto = new DepartmentDto()
                    .setId(department.getId())
                    .setName(department.getName())
                    .setCreateDate(department.getCreateDate())
                    .setUpperDepartment(department.getUpperDepartment());
            dtoList.add(departmentDto);
        }
        return dtoList;
}}
