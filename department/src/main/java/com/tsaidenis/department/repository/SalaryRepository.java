package com.tsaidenis.department.repository;

import com.tsaidenis.department.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository<Salary,Long> {
    Salary findSalaryByDepartmentId(Long id);
}
