package com.tsaidenis.department.mapper;

import com.tsaidenis.department.dto.DepartmentDto;
import com.tsaidenis.department.model.Department;

public interface DepartmentToDepartmentDtoMapper extends Mapper<DepartmentDto, Department>{
    DepartmentDto map(Department department);
}
