package com.tsaidenis.employee.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.envers.Audited;


import java.util.Date;
/**
 * Этот класс сущность сотрудника.
 */
@Data
@Table(name = "employee")
@Entity
@Accessors(chain = true)
@Audited
public class Employee {
    /**
     * Имя последовательности в БД.
     */
    private static final String ID_SEQ = "employee_id_seq";

    /**
     * id сотрудника.
     */
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQ)
    @SequenceGenerator(name = ID_SEQ, sequenceName = ID_SEQ, allocationSize = 1)
    @Id
    long id;

    /**
     * Имя сотрудника.
     */
    private String name;

    /**
     * Фамилия сотрудника.
     */
    private String surname;

    /**
     * Отчество сотрудника (необязательно для заполнения).
     */
    private String patronymic;

    /**
     * Пол сотрудника.
     */
    @Enumerated(EnumType.STRING)
    private Sex sex;

    /**
     * Дата рождения сотрудника.
     */
    private Date dateBirth;

    /**
     * Номер телефона сотрудника.
     */
    private String phone;

    /**
     * Электронная почта сотрудника.
     */
    private String email;

    /**
     * Дата начала работы.
     */
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date dateOfFirstDay;

    /**
     * Дата увольнения сотрудника.
     */
    private Date dateOfLastDay;

    /**
     * Должность сотрудника.
     */
    @Enumerated(EnumType.STRING)
    private Position position;

    /**
     * Размер заработной платы сотрудника.
     */
    private double salary;

    /**
     * Это поле обозначает является ли сотрудник руководителем департамента.
     */
    private Boolean isLeader;

    /**
     * Это поле обозначает удаленного сотрудника.
     */
    private Boolean deleted = false;

    /**
     * Это поле связывает сотрудника с департаментом.
     */
    @JoinColumn(name = "department_id")
    private Long departmentId;
}