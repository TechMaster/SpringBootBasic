package vn.techmaster.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	@Size(min = 2, message = "First Name should have atleast 2 characters")
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@NotNull
	@Size(min = 2, message = "Last Name should have atleast 2 characters")
	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Email
	@NotBlank
	@Column(name = "email_address", nullable = false)
	private String emailId;

	@NotNull
	@Size(min = 2, message = "Passport should have atleast 2 characters")
	@Column(name = "passport_number", nullable = false)
	private String passportNumber;

}
