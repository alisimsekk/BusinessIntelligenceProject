package com.alisimsek.javabootcamp.finalproject.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.alisimsek.javabootcamp.finalproject.helper.DatabaseConnection;
import com.alisimsek.javabootcamp.finalproject.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {

	DatabaseConnection databaseConnection = new DatabaseConnection();

	@Autowired
	public void setDatabaseConnection(DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

//customer tablosuna ekleme yapar ve id döner
	public int createCustomer(Customer customer) {
		final String SQL = "INSERT INTO public.customer(full_name, birth_date, age, gender, email, city, address)\n" +
				"\t VALUES (?, ?, ?, ?, ?, ?, ?);";
		int generatedKey = 0;
		ResultSet rs;
		try {
			PreparedStatement pr = databaseConnection.getConnection().prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS);
			pr.setString(1,customer.getFullName());
			pr.setDate(2,customer.getBirthDate());
			pr.setInt(3,customer.getAge());
			pr.setString(4,customer.getGender());
			pr.setString(5,customer.getEmail());
			pr.setString(6,customer.getCity());
			pr.setString(7,customer.getAdress());
			pr.executeUpdate();
			rs = pr.getGeneratedKeys();
			if (rs.next()) {
				generatedKey = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex.getMessage());
		}
		return generatedKey;
	}

	public boolean deleteCustomer(int id) {
		String query = "DELETE FROM customer WHERE id = ?";
		try {
			PreparedStatement pr = databaseConnection.getConnection().prepareStatement(query);
			pr.setInt(1,id);
			return pr.executeUpdate() != -1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public Customer get(int id){
		Customer obj = null;
        String query = "SELECT * FROM customer WHERE id = ?";
        try {
            PreparedStatement pr = databaseConnection.getConnection().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
            	obj = new Customer();
            	obj.setId(rs.getInt("id"));
            	obj.setFullName(rs.getString("full_name"));
            	obj.setBirthDate(rs.getDate("birth_date"));
            	obj.setAge(rs.getInt("age"));
            	obj.setGender(rs.getString("gender"));
            	obj.setEmail(rs.getString("email"));
            	obj.setCity(rs.getString("city"));
            	obj.setAdress(rs.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

	//filtreleme ekranındaki müşteri şehirleri combo box ına veriyi atmak için
	public List<String> getCityName (){
		List<String> customersCityName = new ArrayList<>();
		customersCityName.add("");
		String query = "SELECT DISTINCT city FROM customer";
		try {
			Statement st = databaseConnection.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()){
				String city = rs.getString("city");
				customersCityName.add(city);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customersCityName;
	}
}
