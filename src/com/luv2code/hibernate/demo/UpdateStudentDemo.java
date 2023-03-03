package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {
    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            int studentID = 1;

            // start a new session and begin transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // retrieve student based on the id: pk - primary id
            System.out.println("\nGetting student with id: " + studentID);

            Student theStudent = session.get(Student.class, studentID);
            System.out.println("Get complete: " + theStudent);

            // update the student
            System.out.println("Updating the student...");
            theStudent.setFirstName("Scooby");


            // commit the transaction - this will update the DB
            session.getTransaction().commit();

            // NEW CODE

            session = factory.getCurrentSession();
            session.beginTransaction();

            // update email for all students
            System.out.println("Updating email for all students...");

            session.createQuery("update Student set email='foo@gmail.com'")
                    .executeUpdate();


            // commit the transaction - this will update the DB
            session.getTransaction().commit();


            System.out.println("Done!");
        } finally {
            factory.close();
        }


    }
}
