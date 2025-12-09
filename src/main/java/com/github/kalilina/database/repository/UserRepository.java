package com.github.kalilina.database.repository;

import lombok.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Map;

@Setter
@NoArgsConstructor
@ToString
public class UserRepository {

    private String username;
    private int poolSize;
    private List<Object> args;
    private Map<String, Object> properties;
    private SessionFactory sessionFactory;
    private Session session;

    public UserRepository(String username, SessionFactory sessionFactory, Map<String, Object> properties,
                          List<Object> args, int poolSize) {
        this.username = username;
        this.sessionFactory = sessionFactory;
        this.properties = properties;
        this.args = args;
        this.poolSize = poolSize;
    }

    public void init() {
        System.out.println("init method was called from UserRepository");
        session = sessionFactory.openSession();
    }

    public void destroy() {
        System.out.println("destroy method was called from UserRepository");
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}
