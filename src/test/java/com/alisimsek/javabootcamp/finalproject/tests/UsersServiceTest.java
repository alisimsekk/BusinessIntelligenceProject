package com.alisimsek.javabootcamp.finalproject.tests;

import com.alisimsek.javabootcamp.finalproject.model.Users;
import com.alisimsek.javabootcamp.finalproject.service.UsersService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UsersServiceTest {

    private UsersService usersService;

    @Before
    public void setup() {
        this.usersService = new UsersService();
    }

    @Test
    public void createUsersTest(){
        Users user = new Users(0,"Test", "Test", "Test");
        int generatedKey = usersService.createUsers(user);
        boolean actualResult = usersService.deleteUser(user);
        assertThat(actualResult).isEqualTo(true);
    }

    @Test
    public void getByEmailPassTest(){
        Users user = new Users(0,"Test", "Test", "Test");
        int generatedKey = usersService.createUsers(user);
        user.setId(generatedKey);
        String email = user.getEmail();
        String pass = user.getPass();
        Users findUser = usersService.getByEmailPass(email, pass);
        assertThat(findUser).isEqualTo(user);
        usersService.deleteUser(user);
    }

}
