package vn.edu.rmit.s3269999;

import java.rmi.RemoteException;
import javax.ejb.EJBObject;

public interface ProductAdminRemote extends EJBObject {

    int addProduct(ProductDT product) throws RemoteException;

    ProductDT getProductByID(int productID) throws RemoteException;

    void deleteProduct(int productID) throws RemoteException;
}
