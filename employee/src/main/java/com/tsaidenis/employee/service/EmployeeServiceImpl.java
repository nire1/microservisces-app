package com.tsaidenis.employee.service;

import com.tsaidenis.employee.dto.EmployeeDto;
import com.tsaidenis.employee.dto.SumDto;
import com.tsaidenis.employee.mapper.EmployeeDtoToEmployeeMapper;
import com.tsaidenis.employee.mapper.EmployeeListToDtoListMapper;
import com.tsaidenis.employee.model.Employee;
import com.tsaidenis.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;

    private final EmployeeDtoToEmployeeMapper employeeDtoToEmployeeMapper;
    private final EmployeeListToDtoListMapper employeeListToDtoListMapper;


    public Employee from(EmployeeDto dto){
        if (validationSalary(dto) && validationDate(dto) && validationChefAmount(dto)) {
            return employeeDtoToEmployeeMapper.map(dto);
        } else {
            String errorMessage =
                            "Ошибка валидации";

            throw new RuntimeException(errorMessage);

        }
    }

    public List<Employee> from(List<EmployeeDto> employees) {
        return employeeListToDtoListMapper.map(employees);
    }

    public boolean validationDate(EmployeeDto dto){
        return dto.getDateBirth().before(dto.getDateOfFirstDay());
    }

    public boolean validationChefAmount(EmployeeDto dto) {
        if (dto.getIsLeader() != null && dto.getIsLeader()) {
            List<Employee> list = employeeRepository.findEmployeesByDepartmentId(dto.getDepartmentId());
            if (!list.isEmpty()) {
                for (Employee employee : list) {
                    if (employee.getIsLeader() != null && employee.getIsLeader()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    @Override
    public boolean validationSalary(EmployeeDto dto) {
        List<Employee> list = employeeRepository.findEmployeesByDepartmentId(dto.getDepartmentId());
        double salaryChef = 0;
        for (Employee employee : list) {
            if (employee.getIsLeader() != null && employee.getIsLeader()) {
                salaryChef = employee.getSalary();
            }
        }
        return salaryChef == 0 || dto.getSalary() < salaryChef;
    }
    @Override
    public Employee create(EmployeeDto dto) {

        Employee employee = from(dto);
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public List<Employee> get() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee get(long id) {
        return employeeRepository.findEmployeeById(id);
    }
    @Override
    public List<Employee> getEmpDep(Long id) {
        return employeeRepository.findEmployeesByDepartmentId(id);
    }
    @Override
    public Employee update(EmployeeDto dto,long id) {
        Employee employee = from(dto);
        employee.setId(id);
        return employee;
    }
    @Override
    public void leave(long id) {
        Employee employee = employeeRepository.findEmployeeById(id);
        employee.setDeleted(true);
        employee.setDateOfLastDay(Date.from(Instant.now()));
        employeeRepository.save(employee);


    }


    @Override
    public Employee moveEmployee(long fromId, long toId) {
        Employee employee = employeeRepository.findEmployeeById(fromId);
        employee.setDepartmentId(toId);
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> moveAllEmployees(long fromId, long toId) {
        List<Employee> employees = employeeRepository.findEmployeesByDepartmentId(fromId);
        for(Employee employee:employees){
            employee.setDepartmentId(toId);
            employeeRepository.save(employee);
        }
        return employees;
    }

    @Override
    public Employee getLeader(long id) {
        List<Employee> employees = employeeRepository
                .findEmployeesByDepartmentId(employeeRepository
                        .findEmployeeById(id)
                        .getDepartmentId());
        Employee lead = null;
        for (Employee employee: employees){
            if(employee.getIsLeader()!=null && employee.getIsLeader()){
                lead = employee;
            }
        }
        return lead;
    }

    @Override
    public Employee getByName(String name) {
        boolean isExist = employeeRepository.existsEmployeeByName(name);
        if(!isExist){
            String errorMessage = String
                    .format(
                            "Сотрудник = %s с таким именем не существует",
                            name
                    );

            throw new RuntimeException(errorMessage);
        }
        return employeeRepository.findEmployeeByName(name);
    }

    @Override
    public void delete(Long id) {
        Employee employee = employeeRepository.findEmployeeById(id);
        employee.setDeleted(true);
        employeeRepository.save(employee);
    }



    @Override
    public SumDto getSumDep(long id) {

        List<Employee> employees = employeeRepository.findEmployeesByDepartmentId(id);
        if (employees == null) {
            return new SumDto(0.00);
        } else {
            double sum = 0.00;
            for (Employee employee : employees) {
                sum += employee.getSalary();
            }
            return new SumDto(sum);
        }
    }
    public double getSumDepartment(long id) {
        return getSumDep(id).getSumSalary();
    }

    public Integer getSumEmployees(Long id) {
        List<Employee> employees = employeeRepository.findEmployeesByDepartmentId(id);
        return employees.size();
    }

    public String getNameDepartmentChef(Long id) {
        Employee lead = getLeader(id);
        return lead.getSurname() + " " + lead.getName() + " " + lead.getPatronymic();
    }

}
