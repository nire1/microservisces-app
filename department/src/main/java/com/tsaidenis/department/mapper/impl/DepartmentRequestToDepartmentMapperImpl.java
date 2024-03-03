package com.tsaidenis.department.mapper.impl;

import com.tsaidenis.department.dto.DepartmentRequest;
import com.tsaidenis.department.mapper.DepartmentRequestToDepartmentMapper;
import com.tsaidenis.department.model.Department;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class DepartmentRequestToDepartmentMapperImpl implements DepartmentRequestToDepartmentMapper {
    @Override
    public Department map(DepartmentRequest request) {
        return new Department()
                .setName(request.getName())
                .setUpperDepartment(request.getUpperDepartment())
                .setCreateDate(Date.from(Instant.now()))
                .setDeleted(false);
    }
}
