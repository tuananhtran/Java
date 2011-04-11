package vn.rmit.edu.s3269999;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

public interface StudentLocalHome extends EJBLocalHome {

    vn.rmit.edu.s3269999.StudentLocal findByPrimaryKey(java.lang.Integer id) throws FinderException;

    java.util.Collection findStudentsByCourse(Integer courseId) throws FinderException;

    vn.rmit.edu.s3269999.StudentLocal create(java.lang.String name) throws CreateException;

    Integer ejbSelectNumberObCoursesEnrolled();
}
