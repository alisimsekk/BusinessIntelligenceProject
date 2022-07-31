package com.alisimsek.javabootcamp.finalproject.service;

import com.alisimsek.javabootcamp.finalproject.model.CarPolicy;
import com.alisimsek.javabootcamp.finalproject.repository.CarPolicyRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class CarPolicyService {

    private CarPolicyRepository carPolicyRepository = new CarPolicyRepository();

    public List<CarPolicy> searchList(String searchQuery) {
        return carPolicyRepository.searchCarPolicyList(searchQuery);
    }

}
