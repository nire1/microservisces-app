package com.tsaidenis.employee.repository;

import com.tsaidenis.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Employee findEmployeeById(Long id);
    List<Employee> findEmployeesByDepartmentId(Long id);
    Employee findEmployeeByName(String name);

    boolean existsEmployeeByName(String name);
}
