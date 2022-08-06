package com.alisimsek.javabootcamp.finalproject.service;

import com.alisimsek.javabootcamp.finalproject.model.InsuranceAgency;
import com.alisimsek.javabootcamp.finalproject.repository.InsuranceAgencyRepository;

import java.util.List;

public class InsuranceAgencyService {
    private InsuranceAgencyRepository insuranceAgencyRepository = new InsuranceAgencyRepository();

    public int createInsuranceAgency(InsuranceAgency newInsuranceAgency) {
        return insuranceAgencyRepository.createInsuranceAgency(newInsuranceAgency);
    }

    public boolean deleteInsuranceAgency(InsuranceAgency insuranceAgency){
        return insuranceAgencyRepository.deleteInsuranceAgency(insuranceAgency);
    }

    public InsuranceAgency get(int id) {
        return insuranceAgencyRepository.get(id);
    }

    public List<String> getAgencyName(){
        return insuranceAgencyRepository.getAgencyName();
    }

}

