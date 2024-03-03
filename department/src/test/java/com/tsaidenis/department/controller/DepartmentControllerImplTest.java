package com.tsaidenis.department.controller;

import com.tsaidenis.department.IntegrationTest;
import com.tsaidenis.department.dto.DepartmentDto;
import com.tsaidenis.department.model.Department;
import com.tsaidenis.department.repository.DepartmentRepository;
import com.tsaidenis.department.util.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@IntegrationTest
class DepartmentControllerImplTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DepartmentRepository departmentRepository;


    @BeforeEach
    void prepare() {
        jdbcTemplate.update("truncate department,salary");

    }

    @Test
    void get() {
        Department department = TestData.getDepartment();

        departmentRepository.save(department);
        departmentRepository.save(TestData.getDepartment1());
        departmentRepository.save(TestData.getDepartment3());
        departmentRepository.save(TestData.getDepartment4());

        ResponseEntity<List<Department>> responseEntity = restTemplate.exchange("/",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Department>>() {
                });
        List<Department> departments = responseEntity.getBody();
        assertNotNull(departments);
        assertEquals(4, departments.size());
        assertEquals("Департамент Самары", departments.get(0).getName());
        assertEquals("Департамент Образования", departments.get(1).getName());
    }

    @Test
    void getId() {


        departmentRepository.save(TestData.getDepartment());
        departmentRepository.save(TestData.getDepartment1());
        Department department = departmentRepository.save(TestData.getDepartment3());
        departmentRepository.save(TestData.getDepartment4());
        Long id = department.getId();

        ResponseEntity<DepartmentDto> responseEntityGetId = restTemplate
                .getForEntity("/departments/{id}", DepartmentDto.class, id);

        DepartmentDto departmentDto = responseEntityGetId.getBody();

        assertNotNull(departmentDto);
        assertEquals(id, departmentDto.getId());
        assertEquals(department.getName(), departmentDto.getName());
    }

    @Test
    void create() {

        HttpEntity<DepartmentDto> httpEntity = new HttpEntity<>(TestData.getDepartmentDto().setCreateDate(new Date()));

        ResponseEntity<DepartmentDto> responseEntity = restTemplate
                .postForEntity("/departments", httpEntity, DepartmentDto.class);

        DepartmentDto responseBody = responseEntity.getBody();

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseBody);
        assertNotNull(responseBody.getId());
        assertNotNull(responseBody.getCreateDate());
    }


}