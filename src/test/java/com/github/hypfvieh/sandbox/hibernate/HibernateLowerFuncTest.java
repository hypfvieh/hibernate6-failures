package com.github.hypfvieh.sandbox.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

import com.github.hypfvieh.sandbox.hibernate.data.SimpleEntry;

import jakarta.persistence.EntityManager;

class HibernateLowerFuncTest {

    static SessionFactory initDb() {
        Configuration configuration = new Configuration();

        // Create the ServiceRegistry from hibernate.cfg.xml
        StandardServiceRegistryBuilder regBuilder = configuration.getStandardServiceRegistryBuilder();
        regBuilder.configure("hibernate.cfg.xml");

        StandardServiceRegistry registry = regBuilder.build();

        try {
            return new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception _ex) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
            throw new RuntimeException("Could not initialize database", _ex);
        }

    }

    @Test
    void testLower() {
       test("lower");
    }

    @Test
    void testUpper() {
       test("upper");
    }

    void test(String _func) {
        try (SessionFactory initDb = initDb()) {
            EntityManager em = initDb.createEntityManager();

            em.getTransaction().begin();
            SimpleEntry entry = new SimpleEntry();
            entry.setDescription("foo");

            em.persist(entry);
            em.getTransaction().commit();

            em.createQuery("FROM SimpleEntry WHERE " + _func + "(description) LIKE " + _func + "(:name)", SimpleEntry.class)
                .setParameter("name", "%foo%")
                .getResultList()
                .forEach(e -> System.out.println(e)); // will print SimpleEntry [id=1, description=foo] when used with hibernate 5.6 and throws when using hibernate 6.0.1

            em.close();
        }
    }
}
