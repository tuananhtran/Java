package vn.com.onlinebookstore;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface BookStoreManagerRemoteHome extends EJBHome {

    vn.com.onlinebookstore.BookStoreManagerRemote create() throws CreateException, RemoteException;
}
