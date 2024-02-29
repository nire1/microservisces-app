package com.tsaidenis.department.controller;

import com.tsaidenis.employee.controller.EmployeeController;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("employee")
public interface EmployeeFeign extends EmployeeController {
}
