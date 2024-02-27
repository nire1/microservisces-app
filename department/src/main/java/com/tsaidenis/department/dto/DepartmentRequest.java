package com.tsaidenis.department.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentRequest {
    Long id;
    @NotBlank
    private String name;

    private Long upperDepartment;
}
