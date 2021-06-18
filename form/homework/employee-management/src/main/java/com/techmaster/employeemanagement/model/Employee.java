package com.techmaster.employeemanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @NotEmpty(message = "Không được bỏ trống trường này")
    private String firstName;
    @Column
    @NotEmpty(message = "Không được bỏ trống trường này")
    private String lastName;

    @Column
    @Email(message = "Email có định dạng không chính xác")
    @NotEmpty(message = "Không được bỏ trống trường này")
    private String emailId;
}
