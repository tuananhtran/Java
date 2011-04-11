package vn.rmit.edu.s3269999;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

public interface CourseLocalHome extends EJBLocalHome {

    vn.rmit.edu.s3269999.CourseLocal findByPrimaryKey(java.lang.Integer id) throws FinderException;

    java.util.Collection findCoursesWithStudentName(String studentName) throws FinderException;

    vn.rmit.edu.s3269999.CourseLocal create(java.lang.String name) throws CreateException;
}
