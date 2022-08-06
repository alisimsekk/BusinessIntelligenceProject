package com.alisimsek.javabootcamp.finalproject.repository;

import java.util.ArrayList;
import java.util.List;
import com.alisimsek.javabootcamp.finalproject.model.Customer;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {

	Session session = HibernateUtil.getSessionFactory().openSession();

	public Integer createCustomer(Customer customer) {
		session.beginTransaction();
		try {
			Integer generatedKey = (Integer) session.save(customer);
			session.getTransaction().commit();
			return generatedKey;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			session.getTransaction().rollback();
		}
		return null;
	}

	public Customer get(int id) {
		Customer foundCustomer = session.get(Customer.class, id);
		return foundCustomer;
	}

	public boolean deleteCustomer(Customer customer) {
		session.beginTransaction();
		try {
			session.delete(customer);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
			return false;
		}
	}

	public List<String> getCityName (){
		session.beginTransaction();
		Query query = session.createQuery("select cu.city from Customer cu group by cu.city");
		List<String> customersCityName = new ArrayList<>();
		customersCityName.add("");
		customersCityName.addAll(query.getResultList());
		session.getTransaction().commit();
		return customersCityName;
	}
}
