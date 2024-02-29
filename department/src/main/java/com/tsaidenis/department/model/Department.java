package com.tsaidenis.department.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * Этот класс сущность департамента.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Department {

    /**
     * id департамента.
     */
    @Id
    private Long id;

    /**
     * Название департамента.
     */
    private String name;

    /**
     * Дата создания департамента.
     */

    private Date createDate;
    /**
     * id департамента в который он входит.
     */
    private Long upperDepartment;

    /**
     * Пометка удаленного департамента.
     */
    private Boolean deleted = false;

}
