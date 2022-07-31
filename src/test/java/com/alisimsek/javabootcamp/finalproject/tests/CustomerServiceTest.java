package com.alisimsek.javabootcamp.finalproject.tests;

import com.alisimsek.javabootcamp.finalproject.model.Customer;
import com.alisimsek.javabootcamp.finalproject.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
public class CustomerServiceTest {

    private CustomerService customerService;

    @Before
    public void setup() {
        this.customerService = new CustomerService();
    }

    @Test
    public void createCustomerTest(){
        Date date = new Date(1993-01-01);
        Customer customer = new Customer(0,"Test Customer", date, 29, "Test Customer",
                "Test Customer","Test Customer", "Test Customer");
        int generatedKey = customerService.createCustomer(customer);
        boolean actualResult = customerService.deleteCustomer(generatedKey);
        assertThat(actualResult).isEqualTo(true);
    }

    @Test
    public void getCustomerTest(){
        Date date = new Date(1993-01-01);
        Customer newCustomer = new Customer(0,"Test Customer", date, 29, "Test Customer",
                "Test Customer","Test Customer", "Test Customer");
        int generatedKey = customerService.createCustomer(newCustomer);
        newCustomer.setId(generatedKey);
        Customer findCustomer = customerService.get(generatedKey);
        assertThat(findCustomer).isEqualTo(newCustomer);
        customerService.deleteCustomer(generatedKey);
    }

    @Test
    public void getCityNameTest(){
        Date date = new Date(1993-01-01);
        Customer newCustomer = new Customer(0,"Test Customer", date, 29, "Test Customer",
                "Test Customer","Test Customer", "Test Customer");
        int generatedKey = customerService.createCustomer(newCustomer);
        List<String> customersCityName = customerService.getCityName();
        assertThat(customersCityName).contains("Test Customer");
        customerService.deleteCustomer(generatedKey);
    }
}
