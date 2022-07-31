package com.alisimsek.javabootcamp.finalproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "insurance_agency")
public class InsuranceAgency {

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name="id")
	private int id;

	@Column(name="name")
	private String name;

	@Column(name="city")
	private String city;

}
