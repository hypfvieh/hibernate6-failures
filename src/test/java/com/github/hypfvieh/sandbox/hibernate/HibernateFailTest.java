package com.github.hypfvieh.sandbox.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

import com.github.hypfvieh.sandbox.hibernate.data.ContentEntry;
import com.github.hypfvieh.sandbox.hibernate.data.DbEntity;

import jakarta.persistence.EntityManager;

class HibernateFailTest {
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
    void testNullList() {
        // no exception when list is null
        test(null);
    }

    @Test
    void testEmptyList() {
        // when selecting records with an empty list, hibernate 6.0.Final throws
        // jakarta.persistence.PersistenceException
        test(List.of());
    }
    
    @Test
    void testFilledList() {
        // no exception if there is any item in list
        test(List.of(new ContentEntry()));
    }

    @Test
    void testFilledNullList() {
        // no exception if there is null in list
        List<ContentEntry> l = new ArrayList<>();
        l.add(null);
        test(l);
    }

    
    void test(List<ContentEntry> _inList) {
        try (SessionFactory initDb = initDb()) {
            EntityManager em = initDb.createEntityManager();

            ContentEntry contentEntry = new ContentEntry();
            contentEntry.setContent("foo");
            
            DbEntity dbEntity = new DbEntity();
            dbEntity.setContent(contentEntry);
            
            em.persist(dbEntity);
            
            em.createQuery("FROM DbEntity WHERE content IN (:vals)", DbEntity.class)
                .setParameter("vals", _inList)
                .getResultList()
                .forEach(e -> System.out.println(e));
            
            em.close();
        }    
    }

}
