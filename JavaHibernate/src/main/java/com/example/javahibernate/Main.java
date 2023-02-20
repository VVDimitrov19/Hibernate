package com.example.javahibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.imageio.spi.ServiceRegistry;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static Session getCurrentSession() {
        // Hibernate 5.4 SessionFactory example without XML
        Map<String, String> settings = new HashMap<>();
        settings.put("connection.driver_class", "com.sqlserver.jdbc.SQLServerDriver");
        settings.put("dialect", "org.hibernate.dialect.SQLServerDialect");
        settings.put("hibernate.connection.url",
                "jdbc:sqlserver://;TD2215\\SQLEXPRESS;encrypt=false;integratedSecurity=true;");
        settings.put("hibernate.current_session_context_class", "thread");
        settings.put("hibernate.show_sql", "true");
        settings.put("hibernate.format_sql", "true");

        ServiceRegistry serviceRegistry = (ServiceRegistry) new StandardServiceRegistryBuilder()
                .applySettings(settings).build();

        MetadataSources metadataSources = new MetadataSources((org.hibernate.service.ServiceRegistry) serviceRegistry);
        // metadataSources.addAnnotatedClass(Player.class);
        Metadata metadata = metadataSources.buildMetadata();

        // here we build the SessionFactory (Hibernate 5.4)
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    public static void main(String[] args) {
        Session session = getCurrentSession();
        session.beginTransaction();
        session.getTransaction().commit();
    }
}
