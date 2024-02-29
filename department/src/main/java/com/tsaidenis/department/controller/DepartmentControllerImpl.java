package com.tsaidenis.department.controller;

import com.tsaidenis.department.dto.DepartmentDto;
import com.tsaidenis.department.dto.DepartmentRequest;
import com.tsaidenis.department.service.DepartmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequiredArgsConstructor
@RestController
public class DepartmentControllerImpl implements DepartmentController{

    private final DepartmentServiceImpl departmentService;



    @Override
    public DepartmentDto create(DepartmentRequest request) {
        return departmentService.from(departmentService.create(request));
    }

    @Override
    public List<DepartmentDto> get() {
        return departmentService.from(departmentService.get());
    }

    @Override
    public DepartmentDto get(Long id) {
        return departmentService.from(departmentService.get(id));
    }

    @Override
    public List<DepartmentDto> getLower(Long id) {
        return departmentService.from(departmentService.getLower(id));
    }

    @Override
    public List<DepartmentDto> getLowers(Long id) {
        return departmentService.from(departmentService.getAllLowerDep(id));
    }

    @Override
    public List<DepartmentDto> getUppers(Long id) {
        return departmentService.from(departmentService.getAllUpperDep(id));
    }

    @Override
    public DepartmentDto getByName(String name) {
        return departmentService.from(departmentService.getName(name));
    }

    @Override
    public DepartmentDto update(DepartmentRequest request, Long id) {
        return departmentService.from(departmentService.update(request,id));
    }

    @Override
    public DepartmentDto updateUpperDepartment(Long id, Long idUp) {
        return updateUpperDepartment(id,idUp);
    }

    @Override
    public String getTest() {
        return "testsd";
    }

    @Override
    public void delete(Long id) {

    }
}
