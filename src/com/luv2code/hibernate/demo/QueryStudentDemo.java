package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class QueryStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            // query students
            @SuppressWarnings("unchecked")
            List<Student> students1 = session.createQuery("from Student").list();

            // display the students
            displayStudents(students1);

            // query students: lastName='Heying';
            @SuppressWarnings("unchecked")
            List<Student> students2 = session.createQuery("from Student s where s.lastName='Doe'").list();

            // display the students
            System.out.println("\n\nStudents with last name of 'Doe'");
            displayStudents(students2);

            // query students: lastName='this' OR firstName='Daffy'
            @SuppressWarnings("unchecked")
            List<Student> students3 = session.createQuery("from Student s where"
                                + " s.lastName='this' OR s.firstName='Daffy'").list();

            // display the students
            System.out.println("\n\nStudents with last name: this OR first name: Daffy");
            displayStudents(students3);

            // query students where email LIKE '%luv2code.com'
            @SuppressWarnings("unchecked")
            List<Student> students4 = session.createQuery("from Student s where"
                    + " s.email LIKE '%luv2code.com'").list();

            // display the students
            System.out.println("\n\nStudents with email 'luv2code.com'");
            displayStudents(students4);




            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            factory.close();
        }


    }

    private static void displayStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
