package studentadmin;

import java.util.Iterator;
import java.util.Scanner;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import vn.rmit.edu.s3269999.*;
import vn.rmit.edu.s3269999.dto.*;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        StudentAdminRemote studentAdmin = null;

        try {
            InitialContext ic = new InitialContext();
            Object remote = ic.lookup("ejb/StudentAdmin");
            StudentAdminRemoteHome studentAdminHome = (StudentAdminRemoteHome) PortableRemoteObject.narrow(remote, StudentAdminRemoteHome.class);
            studentAdmin = studentAdminHome.create();

            CourseDTO course = null;
            StudentDTO student = null;

            System.out.println("Adding courses...\n");
            for (int i = 1; i < 11; i++) {
                course = new CourseDTO();
                course.setName("Course " + (1000 + i));
                studentAdmin.addCourse(course);
                System.out.println("Course #" + i + ":");
                System.out.println(" |_ ID: " + i);
                System.out.println(" |_ Name: " + course.getName());
            }

            System.out.println("\n\nAdding students...\n");
            for (int i = 1; i < 11; i++) {
                student = new StudentDTO();
                student.setName("Student " + (3260000 + i));
                studentAdmin.addStudent(student);
                System.out.println("Student #" + i + ":");
                System.out.println(" |_ ID: " + i);
                System.out.println(" |_ Name: " + student.getName());
            }

            Scanner sc = new Scanner(System.in);

            int course1;
            int course2;
            int course3;
            int course4;

            System.out.println("\n\nEnrolling students to course...");
            System.out.println("[Take a look at the database before we move on!]\n");
            String temp = sc.nextLine();

            for (int i = 1; i < 11; i++) {
                do {
                    course1 = (int) (Math.random() * 10);
                } while (course1 == 0);

                do {
                    course2 = (int) (Math.random() * 10);
                } while (course2 == course1 || course2 == 0);

                do {
                    course3 = (int) (Math.random() * 10);
                } while (course3 == course1 || course3 == course2 || course3 == 0);

                do {
                    course4 = (int) (Math.random() * 10);
                } while (course4 == course1 || course4 == course2 || course4 == course3 || course4 == 0);

                studentAdmin.enrolStudent(new Integer(i), new Integer(course1));
                studentAdmin.enrolStudent(new Integer(i), new Integer(course2));
                studentAdmin.enrolStudent(new Integer(i), new Integer(course3));
                studentAdmin.enrolStudent(new Integer(i), new Integer(course4));

                System.out.println("Student #" + i + ":");
                System.out.println(" |_ Course 1: " + course1);
                System.out.println(" |_ Course 2: " + course2);
                System.out.println(" |_ Course 3: " + course3);
                System.out.println(" |_ Course 4: " + course4);
            }

            int c;

            System.out.println("\n\nFinding student(s) for specified course...");
            System.out.println("[Take a look at the database before we move on!]\n");

            temp = sc.nextLine();
            do {
                c = (int) (Math.random() * 10);
            } while (c == 0);
            Iterator iterator = studentAdmin.getStudentsByCourseID(new Integer(c)).iterator();
            System.out.println("Course id: " + c);
            System.out.println("Students:");
            StudentDTO st = null;
            while (iterator.hasNext()) {
                st = (StudentDTO) iterator.next();
                System.out.println(" |_ Student id: " + st.getId() + " - Student name: " + st.getName());
            }

            System.out.println("\n\nFinding course(s) for specified student name...");
            System.out.println("[Take a look at the database before we move on!]\n");
            temp = sc.nextLine();

            do {
                c = (int) (Math.random() * 10);
            } while (c == 0);

            iterator = studentAdmin.getCoursesByStudentName("Student " + (3260000 + c)).iterator();
            System.out.println("Student name: " + (3260000 + c));
            System.out.println("Courses:");
            CourseDTO cs = null;
            while (iterator.hasNext()) {
                cs = (CourseDTO) iterator.next();
                System.out.println(" |_ Course id: " + cs.getId() + " - Course name: " + cs.getName());
            }

            System.out.println("\n\nRemoving courses...");
            System.out.println("[Take a look at the database before we move on!]\n");
            temp = sc.nextLine();

            for (int i = 0; i < 4; i++) {
                do {
                    c = (int) (Math.random() * 10);
                } while (c == 0);

                System.out.print("Removing course with id " + c + " ... ");
                studentAdmin.removeCourse(new Integer(c));
                System.out.println("Success!");
            }

            int s;

            System.out.println("\n\nRemoving students...");
            System.out.println("[Take a look at the database before we move on!]\n");
            temp = sc.nextLine();

            for (int i = 0; i < 4; i++) {
                do {
                    s = (int) (Math.random() * 10);
                } while (s == 0);

                System.out.print("Removing student with id " + s + " ... ");
                studentAdmin.removeStudent(new Integer(s));
                System.out.println("Success!");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
