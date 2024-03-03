package com.tsaidenis.employee.utill;

import com.tsaidenis.employee.dto.EmployeeDto;
import com.tsaidenis.employee.model.Position;
import com.tsaidenis.employee.model.Sex;
import lombok.experimental.UtilityClass;
import com.tsaidenis.employee.model.Employee;

import java.util.Date;

@UtilityClass
public class TestData {
    public static EmployeeDto getEmployeeDto() {
        return new EmployeeDto()
                .setSurname("Семенов")
                .setName("Антон")
                .setPatronymic("Семенович")
                .setSex("MALE")
                .setDateOfFirstDay(new Date(1451665447567L))
                .setPhone("+79966196131")
                .setEmail("f9jxjd14@gmail.com")
                .setDateBirth(new Date(14916654567L))
                .setPosition("CHEF")
                .setSalary(125000.00)
                .setIsLeader(true)
                .setDepartmentId((long) 1003)
                .setDeleted(false);
    }

    public static EmployeeDto getNewEmployeeDto() {

        return new EmployeeDto()
                .setSurname("Семеновв")
                .setName("Антонн")
                .setPatronymic("Семеновича")
                .setSex("MALE")
                .setDateBirth(new Date(14516654567L))
                .setPhone("+79966196131")
                .setEmail("xl9bc5@gmail.com")
                .setDateOfFirstDay(new Date(14916654567L))
                .setPosition("ADMINISTRATOR")
                .setSalary(12000.00)
                .setIsLeader(false)
                .setDepartmentId((long) 1004)
                .setDeleted(false);
    }

    public static EmployeeDto getErrorEmployeeDto() {

        return new EmployeeDto()
                .setSurname("Глазво")
                .setName("Анто")
                .setPatronymic("Семенови1")
                .setSex("MALE")
                .setDateBirth(new Date(14516654567L))
                .setPhone("+79a66196131")
                .setEmail("xl9bc5@gmail.com")
                .setDateOfFirstDay(new Date(14916654567L))
                .setPosition("CHEF")
                .setSalary(125000.00)
                .setIsLeader(true)
                .setDepartmentId((long) 1004)
                .setDeleted(false);
    }


    public Employee getEmployee() {
        return new Employee()
                .setSurname("Семенов")
                .setName("Антон")
                .setPatronymic("Семенович")
                .setSex(Sex.MALE)
                .setDateBirth(new Date(14516654567L))
                .setPhone("+79966196131")
                .setEmail("f9jxjd14@gmail.com")
                .setDateOfFirstDay(new Date())
                .setPosition(Position.CHEF)
                .setSalary(125000.00)
                .setIsLeader(true)
                .setDepartmentId((long) 1004)
                .setDeleted(false);
    }

    public Employee getNewEmployee() {
        return new Employee()
                .setSurname("Иванов")
                .setName("Семен")
                .setPatronymic("Семенович")
                .setSex(Sex.MALE)
                .setDateOfFirstDay(new Date(1451665447567L))
                .setPhone("+79966196131")
                .setEmail("12f9jxjd14@gmail.com")
                .setDateBirth(new Date(14916654567L))
                .setPosition(Position.OPERATOR)
                .setSalary(100000.00)
                .setIsLeader(false)
                .setDepartmentId((long) 1004)
                .setDeleted(false);
    }

}

