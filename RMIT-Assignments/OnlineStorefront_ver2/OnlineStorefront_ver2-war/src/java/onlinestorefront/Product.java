package onlinestorefront;

import java.io.Serializable;
import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import vn.edu.rmit.s3269999.*;
import vn.edu.rmit.s3269999.dto.ProductDTO;

public class Product implements Serializable {

    private ProductAdminRemote productManager = null;

    public Product() throws NamingException, CreateException, RemoteException {
        InitialContext ic = new InitialContext();
        Object lookup = ic.lookup("ejb/ProductAdmin");
        ProductAdminRemoteHome productAdminHome = (ProductAdminRemoteHome) PortableRemoteObject.narrow(
                lookup, ProductAdminRemoteHome.class);
        productManager = productAdminHome.create();
    }

    public void addProduct(ProductDTO product) throws RemoteException {
        productManager.addProduct(product);
    }

    public ProductDTO getProduct(Long id) throws RemoteException {
        return productManager.getProduct(id);
    }

    public java.util.Collection getAllProducts() throws RemoteException {
        return productManager.getAllProducts();
    }
}
