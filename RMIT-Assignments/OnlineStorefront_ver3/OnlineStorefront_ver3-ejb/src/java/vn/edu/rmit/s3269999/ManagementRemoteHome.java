package vn.edu.rmit.s3269999;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface ManagementRemoteHome extends EJBHome {

    vn.edu.rmit.s3269999.ManagementRemote create() throws CreateException, RemoteException;
}
