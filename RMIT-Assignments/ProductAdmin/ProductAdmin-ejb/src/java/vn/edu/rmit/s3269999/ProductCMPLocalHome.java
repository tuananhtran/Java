package vn.edu.rmit.s3269999;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

public interface ProductCMPLocalHome extends EJBLocalHome {

    vn.edu.rmit.s3269999.ProductCMPLocal findByPrimaryKey(java.lang.Integer productID) throws FinderException;

    vn.edu.rmit.s3269999.ProductCMPLocal create(ProductDT product) throws CreateException;

    int getNextID();
}
