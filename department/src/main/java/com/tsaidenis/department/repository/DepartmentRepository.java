package com.tsaidenis.department.repository;

import com.tsaidenis.department.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findDepartmentById(long id);


    List<Department> findDepartmentsByUpperDepartment(long id);
    Department findDepartmentByName(String name);


}
