package com.tsaidenis.department.service;

import com.tsaidenis.department.dto.DepartmentDto;
import com.tsaidenis.department.dto.DepartmentRequest;
import com.tsaidenis.department.mapper.DepartmentListToDepartmentDtoListMapper;
import com.tsaidenis.department.mapper.DepartmentRequestToDepartmentMapper;
import com.tsaidenis.department.mapper.DepartmentToDepartmentDtoMapper;
import com.tsaidenis.department.model.Department;
import com.tsaidenis.department.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService{
    private final DepartmentRepository departmentRepository;
    private final DepartmentRequestToDepartmentMapper requestToDepartmentMapper;
    private final DepartmentToDepartmentDtoMapper departmentToDepartmentDtoMapper;
    private final DepartmentListToDepartmentDtoListMapper departmentListToDepartmentDtoListMapper;
    private final EmployeeServiceImpl employeeService;

    public DepartmentDto from(Department department) {

        DepartmentDto departmentDto = departmentToDepartmentDtoMapper.map(department);
        departmentDto.setChefName(employeeService.getChefName(departmentDto.getId()));
        departmentDto.setAmountEmployee(Long.valueOf(employeeService.getSumEmployees(departmentDto.getId())));

        return departmentDto;
    }
    public List<DepartmentDto> from(List<Department> departments) {

        List<DepartmentDto> dtoList =departmentListToDepartmentDtoListMapper.map(departments);
        for (DepartmentDto dto:dtoList) {
            dto.setChefName(employeeService.getChefName(dto.getId()));
            dto.setAmountEmployee(Long.valueOf(employeeService.getSumEmployees(dto.getId())));
        }
        return dtoList;
    }
    @Override
    public Department create(DepartmentRequest request) {
        return departmentRepository.save(requestToDepartmentMapper.map(request));
    }

    @Override
    public Department update(DepartmentRequest request, Long id) {
        Department department = requestToDepartmentMapper.map(request);
        department.setId(id);
        return departmentRepository.save(department);
    }

    @Override
    public Department update(Long id, Long idUp) {
        Department department = departmentRepository.findDepartmentById(id);
        department.setUpperDepartment(idUp);
        departmentRepository.save(department);

        return department;
    }

    @Override
    public void delete(Long id) {
        Department department = departmentRepository.findDepartmentById(id);
        department.setDeleted(true);
        departmentRepository.save(department);
    }

    @Override
    public List<Department> get() {
        return departmentRepository.findAll();
    }

    @Override
    public Department get(Long id) {
        return departmentRepository.findDepartmentById(id);
    }

    @Override
    public List<Department> getLower(Long id) {
        return departmentRepository.findDepartmentsByUpperDepartment(id);
    }

    @Override
    public List<Department> getAllLowerDep(Long id) {
        List<Department> departmentList = new ArrayList<>();
        return getLowers(departmentList, id);
    }

    @Override
    public List<Department> getAllUpperDep(Long id) {
        List<Department> departmentList = new ArrayList<>();
        return getUppers(departmentList, id);
    }

    @Override
    public List<Department> getLowers(List<Department> departmentList, Long id) {
        if (getLower(id) != null) {
            for (Department department : getLower(id)) {
                departmentList.add(department);
                getLowers(departmentList, department.getId());
            }
        } else {
            departmentList.add(get(id));
        }
        return departmentList;
    }

    @Override
    public List<Department> getUppers(List<Department> departmentList, Long id) {
        Department department = departmentRepository
                .findDepartmentById(departmentRepository.findDepartmentById(id).getUpperDepartment());

        if (department != null) {
            departmentList.add(department);
            getUppers(departmentList, department.getId());

            return departmentList;
        }
        return departmentList;
    }

    @Override
    public Department getName(String name) {
         return departmentRepository.findDepartmentByName(name);
    }
}
