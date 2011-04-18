package vn.edu.rmit.s3269999;

import java.rmi.RemoteException;
import javax.ejb.EJBObject;
import vn.edu.rmit.s3269999.dto.ProductDTO;

public interface ProductAdminRemote extends EJBObject {

    void addProduct(ProductDTO product) throws RemoteException;

    ProductDTO getProduct(Long id) throws RemoteException;

    java.util.Collection getAllProducts() throws RemoteException;
}
