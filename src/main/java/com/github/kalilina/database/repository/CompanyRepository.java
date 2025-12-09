package com.github.kalilina.database.repository;

import lombok.Setter;
import lombok.ToString;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Setter
@ToString
public class CompanyRepository {

    private SessionFactory sessionFactory;
    private Session session;

    public void init() {
        System.out.println("CompanyRepository: init method was called");
        session = sessionFactory.openSession();
    }

    public void destroy() {
        System.out.println("CompanyRepository: destroy method was called");
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}
