package vn.rmit.edu.s3269999;

import java.util.Collection;
import javax.ejb.EJBLocalObject;

public interface CourseLocal extends EJBLocalObject {

    Integer getId();

    void setId(Integer id);

    String getName();

    void setName(String name);

    Collection getStudents();

    void setStudents(Collection students);
}
