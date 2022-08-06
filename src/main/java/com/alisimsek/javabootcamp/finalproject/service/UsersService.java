package com.alisimsek.javabootcamp.finalproject.service;

import com.alisimsek.javabootcamp.finalproject.model.Users;
import com.alisimsek.javabootcamp.finalproject.repository.UsersRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class UsersService {

    private UsersRepository usersRepository = new UsersRepository();

    public int createUsers (Users user){
        return usersRepository.createUsers(user);
    }

    public Users getByEmailPass (String email, String pass){
        return usersRepository.getByEmailPass(email, pass);
    }

    public boolean deleteUser (Users user){
        return usersRepository.deleteUser(user);
    }

}
