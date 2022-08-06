package com.alisimsek.javabootcamp.finalproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "customer")
public class Customer {


	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name="id")
	private int id;

	@Column(name="full_name")
	private String fullName;

	@Column(name="birth_date")
	private Date birthDate;

	@Column(name="age")
	private int age;

	@Column(name="gender")
	private String gender;

	@Column(name="email")
	private String email;

	@Column(name="city")
	private String city;

	@Column(name="address")
	private String address;

}
