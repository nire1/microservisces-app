package com.tsaidenis.employee.controller;

import com.tsaidenis.employee.IntegrationTest;
import com.tsaidenis.employee.dto.EmployeeDto;
import com.tsaidenis.employee.dto.SumDto;
import com.tsaidenis.employee.model.Employee;
import com.tsaidenis.employee.repository.EmployeeRepository;
import com.tsaidenis.employee.utill.TestData;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@IntegrationTest

class EmployeeControllerImplTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void prepare() {
        jdbcTemplate.update("truncate employee,employee_aud,revinfo");

    }


    @Test
    void create() {
        HttpEntity<EmployeeDto> httpEntity =
                new HttpEntity<>(TestData.getEmployeeDto());
        ResponseEntity<EmployeeDto> responseEntity =
                restTemplate.postForEntity("/employees",httpEntity,EmployeeDto.class);

        EmployeeDto responseBody = responseEntity.getBody();

        assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());

        assertNotNull(responseBody);
        assertNotNull(responseBody.getId());
        assertNotNull(responseBody.getName());
        assertNotNull(responseBody.getEmail());
        assertNotNull(responseBody.getDateBirth());

    }

    @Test
    void update() {
        Employee employee = employeeRepository.save(TestData.getEmployee());

        Long id = employee.getId();
        HttpEntity<EmployeeDto> httpEntityNew =
                new HttpEntity<>(TestData.getNewEmployeeDto());
        ResponseEntity<EmployeeDto> responseEntity =
                restTemplate.exchange("/employees/{id}", HttpMethod.PUT,httpEntityNew,EmployeeDto.class,id);

        EmployeeDto responseBody = responseEntity.getBody();

        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertNotNull(responseBody.getId());
        assertEquals("Антонн",responseBody.getName());
        assertNotNull(responseBody.getEmail());
        assertNotNull(responseBody.getDateBirth());

    }

    @Test
    void updateDepartment() {
        Employee employee = employeeRepository.save(TestData.getEmployee());
        Long id = employee.getId();
        HttpEntity<EmployeeDto> httpEntity =
                new HttpEntity<>(TestData.getEmployeeDto());
        Long idDep = 1004L;

        ResponseEntity<EmployeeDto> responseEntity =
                restTemplate.exchange("/employees/{id}/department/{idDep}",HttpMethod.PUT,httpEntity,EmployeeDto.class,id,idDep);
        EmployeeDto responseEntityBody = responseEntity.getBody();
        assert responseEntityBody != null;
        assertEquals(idDep,responseEntityBody.getDepartmentId());
    }

    @Test
    void updateDepartments() {
        Long id = employeeRepository.save(TestData.getEmployee()).getDepartmentId();
        employeeRepository.save(TestData.getNewEmployee());
        employeeRepository.save(TestData.getNewEmployee());
        employeeRepository.save(TestData.getNewEmployee());
        HttpEntity<EmployeeDto> httpEntity = new HttpEntity<>(TestData.getEmployeeDto());
        Long idDep = 1005L;
        restTemplate.exchange("/employees/1004/departmentall/1005", HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<List<EmployeeDto>>() {
        }, id);
        List<Employee> employees = employeeRepository.findEmployeesByDepartmentId((long) 1005);

        assertEquals(4, employees.size());
        assertEquals(idDep, employees.get(2).getDepartmentId());
    }

    @Test
    void delete() {
        Employee employee = employeeRepository.save(TestData.getEmployee());
        Long id = employee.getId();

        ResponseEntity<EmployeeDto> responseEntity =
                restTemplate.exchange("/employees/{id}",HttpMethod.DELETE,HttpEntity.EMPTY,EmployeeDto.class,id);
        Employee employeeNew = employeeRepository.findEmployeeById(id);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertTrue(employeeNew.getDeleted());

    }

    @Test
    void leave() {


        Employee employee = employeeRepository.save(TestData.getEmployee());

        Long id = employee.getId();

        HttpEntity<EmployeeDto> httpEntity =
                new HttpEntity<>(TestData.getNewEmployeeDto());
        restTemplate.exchange("/employees/{id}/leave", HttpMethod.DELETE, httpEntity, EmployeeDto.class, id);

        employee = employeeRepository.findEmployeeById(id);

        assertNotNull(employee);
        assertNotNull(employee.getDateOfLastDay());


    }
    @Test
    void getLeader() {

        employeeRepository.save(TestData.getEmployee());
        employeeRepository.save(TestData.getNewEmployee());
        Employee employee = employeeRepository.save(TestData.getNewEmployee());
        employeeRepository.save(TestData.getNewEmployee());

        long id = employee.getId();
        ResponseEntity<EmployeeDto> responseEntity = restTemplate.getForEntity("/employees/{id}/chef", EmployeeDto.class, id);
        EmployeeDto employeeDto = responseEntity.getBody();

        assert employeeDto != null;
        assertEquals(true, employeeDto.getIsLeader());
        assertEquals("Антон", employeeDto.getName());
    }

    @Test
    void getByName() {
        Employee employee = employeeRepository.save(TestData.getEmployee());

        String name = employee.getName();

        ResponseEntity<EmployeeDto> responseEntity = restTemplate
                .getForEntity("/employees/{name}", EmployeeDto.class, name);

        EmployeeDto responseBodyTest = responseEntity.getBody();

        assertNotNull(responseBodyTest);
        assertEquals(name, responseBodyTest.getName());
    }

    @Test
    void getEmployeesByDepartment() {
        Long id = employeeRepository.save(TestData.getEmployee()).getDepartmentId();
        employeeRepository.save(TestData.getNewEmployee());
        employeeRepository.save(TestData.getNewEmployee());
        employeeRepository.save(TestData.getNewEmployee());

        ResponseEntity<List<EmployeeDto>> responseEntity = restTemplate.exchange("/employees/{id}/department",
                HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<EmployeeDto>>() {
                }, id);
        List<EmployeeDto> departmentDtoList = responseEntity.getBody();

        assertNotNull(departmentDtoList);
        assertEquals(4, departmentDtoList.size());
    }

    @Test
    void getSumSalary() {
        employeeRepository.save(TestData.getEmployee());
        employeeRepository.save(TestData.getNewEmployee());
        Employee employee = employeeRepository.save(TestData.getNewEmployee());
        long idDep = employee.getDepartmentId();

        ResponseEntity<SumDto> responseEntity = restTemplate
                .getForEntity("/employees/{idDep}/sum", SumDto.class, idDep);

        SumDto responseBody = responseEntity.getBody();

        assert responseBody != null;
        assertEquals(325000, responseBody.getSumSalary());
    }
    @Test
    void get() {


        employeeRepository.save(TestData.getEmployee());
        employeeRepository.save(TestData.getNewEmployee());
        employeeRepository.save(TestData.getNewEmployee());
        employeeRepository.save(TestData.getNewEmployee());

        ResponseEntity<List<EmployeeDto>> responseEntity = restTemplate.exchange("/employees", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<EmployeeDto>>() {
                });

        List<EmployeeDto> employees = responseEntity.getBody();

        assertNotNull(employees);
        assertEquals(4, employees.size());
        assertEquals("Антон", employees.get(0).getName());
        assertEquals("Семен", employees.get(1).getName());

    }

    @Test
    void getById() {


        employeeRepository.save(TestData.getEmployee());
        employeeRepository.save(TestData.getNewEmployee());
        Employee employee = employeeRepository.save(TestData.getNewEmployee());
        employeeRepository.save(TestData.getNewEmployee());
        Long id = employee.getId();

        ResponseEntity<EmployeeDto> responseEntityGetId = restTemplate.getForEntity("/{id}", EmployeeDto.class, id);

        EmployeeDto responseBody = responseEntityGetId.getBody();

        assertNotNull(responseBody);
        assertEquals(employee.getName(), responseBody.getName());


    }
}