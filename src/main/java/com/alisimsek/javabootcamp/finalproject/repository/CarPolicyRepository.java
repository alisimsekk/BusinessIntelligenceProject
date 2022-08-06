package com.alisimsek.javabootcamp.finalproject.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.alisimsek.javabootcamp.finalproject.model.CarPolicy;
import com.alisimsek.javabootcamp.finalproject.model.Customer;
import com.alisimsek.javabootcamp.finalproject.model.InsuranceAgency;
import com.alisimsek.javabootcamp.finalproject.service.CustomerService;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CarPolicyRepository {

	private CustomerService customerService = new CustomerService();
	private InsuranceAgencyRepository insuranceAgencyRepository = new InsuranceAgencyRepository();

	Session session = HibernateUtil.getSessionFactory().openSession();

	public List<CarPolicy> searchCarPolicyList(String sql) {
		List<CarPolicy> carPolicyList = new ArrayList<>();
		session.beginTransaction();
		Query query = session.createNativeQuery(sql);
		List<Object[]> list = query.getResultList();
		session.getTransaction().commit();
		for (Object[] objects : list) {
			int id = (int) objects[0];
			Customer customer = customerService.get((int) objects[1]);
			InsuranceAgency insuranceAgency = insuranceAgencyRepository.get((int) objects[2]);
			String carMake = objects[3].toString();
			int carYear = (int) objects[4];
			Date transactionDate = (Date) objects[5];
			Date effectiveDate = (Date) objects[6];
			Date expirationDate = (Date) objects[7];
			double price = (double) objects[8];
			carPolicyList.add(
					new CarPolicy(
							id,
							customer,
							insuranceAgency,
							carMake,
							carYear,
							transactionDate,
							effectiveDate,
							expirationDate,
							price)
			);
		}
		return carPolicyList;
	}
}
