package com.alisimsek.javabootcamp.finalproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class Users {

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name="id")
	private int id;

	@Column(name="name")
    private String name;

	@Column(name="email")
    private String email;

	@Column(name="pass")
    private String pass;

}
