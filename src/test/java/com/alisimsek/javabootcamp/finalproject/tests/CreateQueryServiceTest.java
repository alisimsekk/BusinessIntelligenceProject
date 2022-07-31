package com.alisimsek.javabootcamp.finalproject.tests;

import com.alisimsek.javabootcamp.finalproject.service.CreateQuery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class CreateQueryServiceTest {

    private CreateQuery createQuery;

    @Before
    public void setup() {
        this.createQuery = new CreateQuery();
    }

    @Test
    public void searchTest(){
        String generetedQuery = createQuery.search( "2030-07-05", "2030-07-15",
                "Test", "Test","","");

        String actualResult ="SELECT car_policy.id, customer.id AS customer_id," +
                "insurance_agency.id AS insurance_agency_id, " +
                "car_make, car_year,transaction_date, effective_date, expiration_date, " +
                "price FROM car_policy " +
                "INNER JOIN customer ON car_policy.customer_id = customer.id " +
                "INNER JOIN insurance_agency ON car_policy.insurance_agency_id = insurance_agency.id " +
                "WHERE (car_policy.transaction_date between '2030-07-05' and '2030-07-15') " +
                "AND (customer.city like '%Test%')" +
                "AND (insurance_agency.name like '%Test%')" +
                "order by car_policy.id asc";

        assertThat(actualResult).isEqualTo(generetedQuery);
    }

}
