package org.example.project24;

import org.example.project24.Entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.MutationQuery;

public class TestEmployee {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try {

            Session session = factory.getCurrentSession();
            session.beginTransaction();

//            Employee employee = session.get(Employee.class, 1);
//            employee.setSalary(1500);

            MutationQuery query = session.createMutationQuery(
                    "UPDATE Employee SET salary = 1000 WHERE name = 'Varvara' OR name = 'Dmitrii'");

            int updated = query.executeUpdate();

            session.getTransaction().commit();

            System.out.println("Успешно!");

        }
        finally {
            factory.close();
        }

    }
}
