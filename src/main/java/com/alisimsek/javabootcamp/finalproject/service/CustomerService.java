package com.alisimsek.javabootcamp.finalproject.service;

import com.alisimsek.javabootcamp.finalproject.model.Customer;
import com.alisimsek.javabootcamp.finalproject.repository.CustomerRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class CustomerService {

    private CustomerRepository customerRepository = new CustomerRepository();

    public int createCustomer(Customer customer){
        return customerRepository.createCustomer(customer);
    }

    public Customer get(  int id) {
        return customerRepository.get(id);
    }

    public boolean deleteCustomer (int id){
        return customerRepository.deleteCustomer(id);
    }

    public List<String> getCityName (){
        return customerRepository.getCityName();
    }
}
