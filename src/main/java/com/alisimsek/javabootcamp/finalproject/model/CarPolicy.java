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

	@ManyToOne
	@JoinColumn(name = "customer_id", insertable = false, updatable = false)
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "insurance_agency_id", insertable = false, updatable = false)
	private InsuranceAgency insuranceAgency;

	@Column(name="car_make")
	private String carMake;

	@Column(name="car_year")
	private int carYear;

	@Column(name="transaction_date")
	private Date transactionDate;

	@Column(name="effective_date")
	private Date effectiveDate;

	@Column(name="expiration_date")
	private Date expirationDate;

	@Column(name="price")
	private double price;

}
