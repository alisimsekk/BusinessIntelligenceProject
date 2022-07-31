package com.alisimsek.javabootcamp.finalproject.tests;

import com.alisimsek.javabootcamp.finalproject.model.CarPolicy;
import com.alisimsek.javabootcamp.finalproject.service.CarPolicyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class CarPolicyServiceTest {

    private CarPolicyService carPolicyService;

    @Before
    public void setup() {
        this.carPolicyService = new CarPolicyService();
    }

    @Test
    public void searchListTest(){
        String query = "SELECT * FROM car_policy LIMIT 1";
        List<CarPolicy> carPolicy = carPolicyService.searchList(query);
        int actualResult = carPolicy.size();
        assertThat(actualResult).isEqualTo(1);
    }
}
