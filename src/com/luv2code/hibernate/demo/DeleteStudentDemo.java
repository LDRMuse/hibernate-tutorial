package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {
    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            int studentID = 10;

            // start a new session and begin transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // retrieve student based on the id: pk - primary id
            System.out.println("\nGetting student with id: " + studentID);

            Student theStudent = session.get(Student.class, studentID);
            System.out.println("Get complete: " + theStudent);

            // delete the student
//            System.out.println("Deleting the student...");
//            session.delete(theStudent);

            // commit the transaction - this will update the DB
//            session.getTransaction().commit();

            // MY NEW CODE
            // alternate delete approach
            System.out.println("Deleting student with id: 2");
            session.createQuery("delete from Student where id='2'")
                            .executeUpdate();

            // commit the transaction - this will update the DB
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }


    }

}
