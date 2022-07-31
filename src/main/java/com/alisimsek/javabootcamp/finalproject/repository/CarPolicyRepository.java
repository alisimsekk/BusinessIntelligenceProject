package com.alisimsek.javabootcamp.finalproject.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alisimsek.javabootcamp.finalproject.helper.DatabaseConnection;
import com.alisimsek.javabootcamp.finalproject.model.CarPolicy;

import com.alisimsek.javabootcamp.finalproject.model.Customer;
import com.alisimsek.javabootcamp.finalproject.model.InsuranceAgency;
import com.alisimsek.javabootcamp.finalproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CarPolicyRepository {

	DatabaseConnection databaseConnection = new DatabaseConnection();

	@Autowired
	public void setDatabaseConnection(DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

	private CustomerService customerService = new CustomerService();
	private InsuranceAgencyRepository insuranceAgencyRepository = new InsuranceAgencyRepository();

	public List<CarPolicy> searchCarPolicyList(String query) {
		List<CarPolicy> carPolicyList = new ArrayList<>();
		Connection connection = null;
		try {
			connection = databaseConnection.getConnection();
			PreparedStatement pr = connection.prepareStatement(query);
			ResultSet rs = pr.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				Customer customer = customerService.get( rs.getInt("customer_id"));
				InsuranceAgency insuranceAgency = insuranceAgencyRepository.get(rs.getInt("insurance_agency_id"));
				String carMake = rs.getString("car_make");
				int carYear = rs.getInt("car_year");
				Date transactionDate = rs.getDate("transaction_date");
				Date effectiveDate = rs.getDate("effective_date");
				Date expirationDate = rs.getDate("expiration_date");
				float price = rs.getFloat("price");
				carPolicyList.add(
						new CarPolicy(
								id, 
								customer,
								insuranceAgency,
								carMake, 
								carYear, 
								transactionDate, 
								effectiveDate, 
								expirationDate, 
								price)
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return carPolicyList;
	}

}
