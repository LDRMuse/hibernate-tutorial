package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // create student object
            System.out.println("Creating a new Student Object");
            Student student = new Student("try", "this", "trythis@luv2code.com");

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the student object...");
            System.out.println(student);
            session.save(student);

            // commit transaction
            session.getTransaction().commit();

            // MY NEW CODE
            // find out the student's id: pk - primary key
            System.out.println("Saved student. Generated id: " + student.getId());

            // now get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // retrieve student based on the id: pk - primary id
            System.out.println("\nGetting student with id: " + student.getId());

            Student theGotStudent = session.get(Student.class, student.getId());
            System.out.println("Get complete: " + theGotStudent);

            // commit the transaction
            session.getTransaction().commit();


            System.out.println("Done!");

        } finally {
            factory.close();
        }


    }
}
