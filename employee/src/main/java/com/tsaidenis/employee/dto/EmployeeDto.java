package com.tsaidenis.employee.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;
@Data
@Accessors(chain = true)
public class EmployeeDto {
        /**
         * id сотрудника.
         */
        private Long id;

        /**
         * Фамилия сотрудника.
         */
        @NotNull
        @Pattern(regexp = "^[А-ЯЁ][а-яё\\-]*$")
        private String surname;

        /**
         * Имя сотрудника.
         */
        @NotNull
        @Pattern(regexp = "^[А-ЯЁ][а-яё\\-]*$")
        private String name;

        /**
         * Отчество сотрудника
         * (необязательно для заполнения).
         */
        @Pattern(regexp = "^[А-ЯЁ][а-яё\\-]*$")
        private String patronymic;

        /**
         * Пол сотрудника.
         */
        private String sex;

        /**
         * Дата рождения сотрудника.
         */
        @JsonFormat(pattern = "yyyy-MM-dd")

        private Date dateBirth;

        /**
         * Номер телефона сотрудника.
         */
        @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$")
        private String phone;

        /**
         * Электронная почта сотрудника.
         */
        @Email
        private String email;

        /**
         * Дата начала работы.
         */
        @JsonFormat(pattern = "yyyy-MM-dd")
        private Date dateOfFirstDay;

        /**
         * Должность сотрудника.
         */
        private String position;

        /**
         * Размер заработной платы сотрудника.
         */
        @Min(value = 0)
        private Double salary;

        /**
         * Это поле обозначает является
         * ли сотрудник руководителем департамента.
         */
        private Boolean isLeader;

        /**
         * Это поле обозначает удаленного сотрудника.
         */
        private Boolean deleted;

        /**
         * Id департамента в котором состоит сотрудник.
         */
        private Long departmentId;
    }


