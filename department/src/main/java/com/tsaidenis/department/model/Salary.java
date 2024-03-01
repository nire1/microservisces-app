package com.tsaidenis.department.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Entity
@Table(name = "salary")
@Accessors(chain = true)
public class Salary {
    /**
     * Имя последовательности в БД.
     */
    private static final String ID_SEQ = "salary_id_seq";

    /**
     * id департамента.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQ)
    @SequenceGenerator(name = ID_SEQ, sequenceName = ID_SEQ, allocationSize = 1)
    private Long id;

    /**
     * Название департамента.
     */
    private String name;

    /**
     * Дата создания департамента.
     */
    private double salaryFond;

    /**
     * Это поле связывает сотрудника с департаментом.
     */
    private Long departmentId;
}
