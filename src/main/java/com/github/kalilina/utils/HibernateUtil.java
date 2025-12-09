package com.github.kalilina.utils;

import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY;

    static {
        Configuration cfg = new Configuration();

        // ничего лучше не придумал
        cfg.setProperty("hibernate.connection.driver_class", PropertiesUtil.get("db.driver"));
        cfg.setProperty("hibernate.connection.url", PropertiesUtil.get("db.url"));
        cfg.setProperty("hibernate.connection.username", PropertiesUtil.get("db.username"));
        cfg.setProperty("hibernate.connection.password", PropertiesUtil.get("db.password"));

        cfg.setProperty("hibernate.show_sql", "true");
        cfg.setProperty("hibernate.format_sql", "true");

        SESSION_FACTORY = cfg.buildSessionFactory();
    }

    public SessionFactory get() {
        return SESSION_FACTORY;
    }
}
