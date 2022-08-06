package com.alisimsek.javabootcamp.finalproject.repository;

import java.util.ArrayList;
import java.util.List;
import com.alisimsek.javabootcamp.finalproject.model.InsuranceAgency;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class InsuranceAgencyRepository {

    Session session = HibernateUtil.getSessionFactory().openSession();

    public Integer createInsuranceAgency(InsuranceAgency newInsuranceAgency){
        session.beginTransaction();
        try {
            Integer generatedKey = (Integer) session.save(newInsuranceAgency);
            session.getTransaction().commit();
            return generatedKey;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }
        return null;
    }

    public boolean deleteInsuranceAgency(InsuranceAgency insuranceAgency) {
        session.beginTransaction();
        try {
            session.delete(insuranceAgency);
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

    public InsuranceAgency get(int id) {
        InsuranceAgency foundInsuranceAgency = session.get(InsuranceAgency.class, id);
        return foundInsuranceAgency;
    }

    public List<String> getAgencyName(){
        session.beginTransaction();
        Query query = session.createQuery("select agency.name from InsuranceAgency agency group by agency.name");
        List<String> agencyName = new ArrayList<>();
        agencyName.add("");
        agencyName.addAll(query.getResultList());
        session.getTransaction().commit();
        return agencyName;
    }
}
