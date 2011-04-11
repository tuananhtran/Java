package vn.rmit.edu.s3269999;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import vn.rmit.edu.s3269999.dto.*;

public class StudentAdmin implements SessionBean {

    private SessionContext context;

    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click the + sign on the left to edit the code.">;
    // TODO Add code to acquire and use other enterprise resources (DataSource, JMS, enterprise bean, Web services)
    // TODO Add business methods or web service operations
    /**
     * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
     */
    public void setSessionContext(SessionContext aContext) {
        context = aContext;
    }

    /**
     * @see javax.ejb.SessionBean#ejbActivate()
     */
    public void ejbActivate() {
    }

    /**
     * @see javax.ejb.SessionBean#ejbPassivate()
     */
    public void ejbPassivate() {
    }

    /**
     * @see javax.ejb.SessionBean#ejbRemove()
     */
    public void ejbRemove() {
    }
    // </editor-fold>;
    /**
     * See section 7.10.3 of the EJB 2.0 specification
     * See section 7.11.3 of the EJB 2.1 specification
     */
    private StudentLocalHome studentManager;
    private CourseLocalHome courseManager;

    public void ejbCreate() {
        // TODO implement ejbCreate if necessary, acquire resources
        // This method has access to the JNDI context so resource aquisition
        // spanning all methods can be performed here such as home interfaces
        // and data sources.
        try {
            InitialContext ic = new InitialContext();
            studentManager = (StudentLocalHome) ic.lookup("java:comp/env/ejb/StudentLocalHome");
            courseManager = (CourseLocalHome) ic.lookup("java:comp/env/ejb/CourseLocalHome");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")

    public void addStudent(StudentDTO student) {
        try {
            studentManager.create(student.getName());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void removeStudent(Integer studentId) {
        try {
            StudentLocal student = studentManager.findByPrimaryKey(studentId);
            if (student != null) {
                student.remove();
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void addCourse(CourseDTO course) {
        try {
            courseManager.create(course.getName());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void removeCourse(Integer courseId) {
        try {
            CourseLocal course = courseManager.findByPrimaryKey(courseId);
            if (course != null) {
                course.remove();
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void enrolStudent(Integer studentId, Integer courseId) {
        try {
            Collection students = studentManager.findStudentsByCourse(courseId);
            Collection courses = studentManager.findByPrimaryKey(studentId).getCourses();

            StudentLocal student = studentManager.findByPrimaryKey(studentId);
            CourseLocal course = courseManager.findByPrimaryKey(courseId);

            if (!students.contains(student)) {
                students.add(student);
            }

            if (!courses.contains(course)) {
                courses.add(course);
            }

            student.setCourses(courses);
            course.setStudents(students);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public Collection getStudentsByCourseID(Integer courseId) {
        Collection students = new ArrayList();

        try {
            Iterator iterator = studentManager.findStudentsByCourse(courseId).iterator();
            StudentDTO st = null;
            StudentLocal sl = null;
            while (iterator.hasNext()) {
                sl = (StudentLocal) iterator.next();
                st = new StudentDTO();
                st.setId(sl.getId());
                st.setName(sl.getName());
                students.add(st);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        return students;
    }

    public Collection getCoursesByStudentName(String studentName) {
        Collection courses = new ArrayList();

        try {
            Iterator iterator = courseManager.findCoursesWithStudentName(studentName).iterator();
            CourseDTO cs = null;
            CourseLocal cl = null;
            while (iterator.hasNext()) {
                cl = (CourseLocal) iterator.next();
                cs = new CourseDTO();
                cs.setId(cl.getId());
                cs.setName(cl.getName());
                courses.add(cs);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        return courses;
    }
}
