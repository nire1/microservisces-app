package com.tsaidenis.department.mapper;

import com.tsaidenis.department.dto.DepartmentDto;
import com.tsaidenis.department.model.Department;

import java.util.List;

public interface DepartmentListToDepartmentDtoListMapper extends Mapper<List<DepartmentDto>,List<Department>>{
    List<DepartmentDto> map(List<Department> departmentList);
}
