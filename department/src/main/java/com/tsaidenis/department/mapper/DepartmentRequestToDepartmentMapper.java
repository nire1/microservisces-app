package com.tsaidenis.department.mapper;

import com.tsaidenis.department.dto.DepartmentRequest;
import com.tsaidenis.department.model.Department;

public interface DepartmentRequestToDepartmentMapper extends Mapper<Department, DepartmentRequest>{
    Department map(DepartmentRequest request);
}
