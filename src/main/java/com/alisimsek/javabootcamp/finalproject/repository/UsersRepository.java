package com.alisimsek.javabootcamp.finalproject.repository;

import com.alisimsek.javabootcamp.finalproject.model.Users;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRepository {

    Session session = HibernateUtil.getSessionFactory().openSession();

    public Users getByEmailPass (String email, String pass){
        session.beginTransaction();
        Query<Users> query = session.createQuery("from Users where email = :email and pass = :pass", Users.class);
        query.setParameter("email", email);
        query.setParameter("pass", pass);
        Users user = query.uniqueResult();
        session.getTransaction().commit();
        return user;
    }

    public Integer createUsers(Users user) {
        session.beginTransaction();
        try {
            Integer generatedKey = (Integer) session.save(user);
            session.getTransaction().commit();
            return generatedKey;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }
        return null;
    }

    public boolean deleteUser(Users user) {
        session.beginTransaction();
        try {
            session.delete(user);
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
}
