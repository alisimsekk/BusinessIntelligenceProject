package com.alisimsek.javabootcamp.finalproject.tests;

import com.alisimsek.javabootcamp.finalproject.model.InsuranceAgency;
import com.alisimsek.javabootcamp.finalproject.service.InsuranceAgencyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class InsuranceAgencyServiceTest {

    private InsuranceAgencyService insuranceAgencyService;

    @Before
    public void setup() {
        this.insuranceAgencyService = new InsuranceAgencyService();
    }

    @Test
    public void createInsuranceAgencyTest(){
        InsuranceAgency newInsuranceAgency = new InsuranceAgency(0,"Test InsuranceAgency",
                "Test InsuranceAgency");
        int generatedKey = insuranceAgencyService.createInsuranceAgency(newInsuranceAgency);
        boolean actualResult = insuranceAgencyService.deleteInsuranceAgency(newInsuranceAgency);
        assertThat(actualResult).isEqualTo(true);
    }

    @Test
    public void getInsuranceAgencyTest() {
        InsuranceAgency newInsuranceAgency = new InsuranceAgency(0,"Test InsuranceAgency",
                "Test InsuranceAgency");
        int generatedKey = insuranceAgencyService.createInsuranceAgency(newInsuranceAgency);
        newInsuranceAgency.setId(generatedKey);
        InsuranceAgency findInsuranceAgency = insuranceAgencyService.get(generatedKey);
        assertThat(findInsuranceAgency).isEqualTo(newInsuranceAgency);
        insuranceAgencyService.deleteInsuranceAgency(newInsuranceAgency);
    }

    @Test
    public void getAgencyNameTest(){
        InsuranceAgency newInsuranceAgency = new InsuranceAgency(0,"Test InsuranceAgency",
                "Test InsuranceAgency");
        int generatedKey = insuranceAgencyService.createInsuranceAgency(newInsuranceAgency);
        List<String> insuranceAgencyName = insuranceAgencyService.getAgencyName();
        assertThat(insuranceAgencyName).contains("Test InsuranceAgency");
        insuranceAgencyService.deleteInsuranceAgency(newInsuranceAgency);

    }

}
