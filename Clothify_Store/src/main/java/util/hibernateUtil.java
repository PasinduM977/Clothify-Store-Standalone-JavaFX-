package util;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class hibernateUtil {
    private static SessionFactory sessionFactory=createSession();

    private static SessionFactory createSession() {

        StandardServiceRegistry build = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(build).addAnnotatedClass(supplierEntity.class).
                addAnnotatedClass(productEntity.class).
                addAnnotatedClass(userEntity.class).
                addAnnotatedClass(orderEntity.class).
                addAnnotatedClass(orderDetailsEntity.class).
                getMetadataBuilder().
                applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE).
                build();

        return metadata.getSessionFactoryBuilder().build();


    }

   public static Session getSession()
   {
       return sessionFactory.openSession();
   }
}
