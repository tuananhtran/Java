package vn.rmit.edu.s3269999;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface StudentAdminRemoteHome extends EJBHome {

    vn.rmit.edu.s3269999.StudentAdminRemote create() throws CreateException, RemoteException;
}
