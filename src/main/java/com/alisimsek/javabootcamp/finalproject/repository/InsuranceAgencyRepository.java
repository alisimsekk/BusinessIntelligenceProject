package com.alisimsek.javabootcamp.finalproject.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.alisimsek.javabootcamp.finalproject.helper.DatabaseConnection;
import com.alisimsek.javabootcamp.finalproject.model.InsuranceAgency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InsuranceAgencyRepository {

    DatabaseConnection databaseConnection = new DatabaseConnection();

    @Autowired
    public void setDatabaseConnection(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public int createInsuranceAgency(InsuranceAgency newInsuranceAgency){
        String query = "INSERT INTO public.insurance_agency (name, city) VALUES (?, ?)";
        int generatedKey = 0;
        ResultSet rs;
        try {
            PreparedStatement pr = databaseConnection.getConnection().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            pr.setString(1,newInsuranceAgency.getName());
            pr.setString(2,newInsuranceAgency.getCity());
            pr.executeUpdate();
            rs = pr.getGeneratedKeys();
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return generatedKey;
    }

    public boolean deleteInsuranceAgency(int id) {
        String query = "DELETE FROM insurance_agency WHERE id = ?";
        try {
            PreparedStatement pr = databaseConnection.getConnection().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

	public InsuranceAgency get(int id){
		InsuranceAgency obj = null;
        String query = "SELECT * FROM insurance_agency WHERE id = ?";
        try {
            PreparedStatement pr = databaseConnection.getConnection().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
            	obj = new InsuranceAgency();
            	obj.setId(rs.getInt("id"));
            	obj.setName(rs.getString("name"));
            	obj.setCity(rs.getString("city"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    //filtreleme ekranındaki acenta isimleri combo box ına veriyi atmak için
    public List<String> getAgencyName() {
        List<String> agencyNameList = new ArrayList<>();
        agencyNameList.add("");
        String query = " SELECT DISTINCT name FROM insurance_agency";
        try {
            Statement st = databaseConnection.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                String agencyName = rs.getString("name");
                agencyNameList.add(agencyName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agencyNameList;
    }
}
