package com.alisimsek.javabootcamp.finalproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class CarPolicy {

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name="id")
	private int id;

	@Column(name="customer_id")
	private Customer customer;

	@Column(name="insurance_agency_id")
	private InsuranceAgency insuranceAgency;

	@Column(name="carMake")
	private String carMake;

	@Column(name="carYear")
	private int carYear;

	@Column(name="transactionDate")
	private Date transactionDate;

	@Column(name="effectiveDate")
	private Date effectiveDate;

	@Column(name="expirationDate")
	private Date expirationDate;

	@Column(name="price")
	private float price;

}
