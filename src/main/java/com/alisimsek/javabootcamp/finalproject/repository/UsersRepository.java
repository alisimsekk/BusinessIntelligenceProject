package com.alisimsek.javabootcamp.finalproject.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.alisimsek.javabootcamp.finalproject.helper.DatabaseConnection;
import com.alisimsek.javabootcamp.finalproject.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRepository {

    DatabaseConnection databaseConnection = new DatabaseConnection();


    @Autowired
    public void setDatabaseConnection(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }


    //login ekranında girilen email ve pass'e göre db den veri çeker
    public Users getByEmailPass(String email, String pass){
        Users obj = null;
        String query = "SELECT * FROM users WHERE email = ? AND pass = ?";
        try {
            PreparedStatement pr = databaseConnection.getConnection().prepareStatement(query);
            pr.setString(1, email);
            pr.setString(2, pass);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new Users();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setEmail(rs.getString("email"));
                obj.setPass(rs.getString("pass"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

//user tablosuna ekleme yapar ve id döner
    public int createUsers(Users user){
        final String SQL = "INSERT INTO users (name, email, pass) VALUES (?,?,?)";
        int generatedKey = 0;
        ResultSet rs;
        try {
            PreparedStatement pr = databaseConnection.getConnection().prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS);
            pr.setString(1, user.getName());
            pr.setString(2, user.getEmail());
            pr.setString(3, user.getPass());
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

    public boolean deleteUser(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        try {
            PreparedStatement pr = databaseConnection.getConnection().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
