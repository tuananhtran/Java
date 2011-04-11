package vn.rmit.edu.s3269999;

import java.util.Collection;
import javax.ejb.EJBLocalObject;

public interface StudentLocal extends EJBLocalObject {

    Integer getId();

    void setId(Integer id);

    String getName();

    void setName(String name);

    Collection getCourses();

    void setCourses(Collection courses);

    //void enrolInCourse(Integer courseID);

    int getNumberOfCoursesEnrolled();
}
