package com.tsaidenis.department.service;

import com.tsaidenis.department.model.Department;
import com.tsaidenis.department.model.Salary;
import com.tsaidenis.department.repository.DepartmentRepository;
import com.tsaidenis.department.repository.SalaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Component
public class SalaryService {

    private final DepartmentRepository departmentRepository;

    private final SalaryRepository salaryRepository;

    private final EmployeeServiceImpl employeeService;

    @Transactional
    @Scheduled(fixedRate = 300000)
    public void saveSalary() {
        List<Department> departmentList = departmentRepository.findAll();
        Salary salary;

        for (Department dto : departmentList) {
            if (salaryRepository.findSalaryByDepartmentId(dto.getId()) != null) {
                salary = salaryRepository.findSalaryByDepartmentId(dto.getId());
            } else {
                salary = new Salary();
            }

            salary.setName(dto.getName())
                    .setDepartmentId(dto.getId())
                    .setSalaryFond(employeeService.getSumSalary(dto.getId()));
            salaryRepository.save(salary);
        }
    }
}