package vn.rmit.edu.s3269999;

import java.rmi.RemoteException;
import java.util.Collection;
import javax.ejb.EJBObject;
import vn.rmit.edu.s3269999.dto.*;

public interface StudentAdminRemote extends EJBObject {

    void addStudent(StudentDTO student) throws RemoteException;

    void removeStudent(Integer studentId) throws RemoteException;

    void addCourse(CourseDTO course) throws RemoteException;

    void removeCourse(Integer courseId) throws RemoteException;

    void enrolStudent(Integer studentId, Integer courseId) throws RemoteException;

    Collection getStudentsByCourseID(Integer courseId) throws RemoteException;

    Collection getCoursesByStudentName(String studentName) throws RemoteException;
}
