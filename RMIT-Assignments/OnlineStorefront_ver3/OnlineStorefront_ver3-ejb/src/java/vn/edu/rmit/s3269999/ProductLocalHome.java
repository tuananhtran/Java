package vn.edu.rmit.s3269999;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

public interface ProductLocalHome extends EJBLocalHome {

    vn.edu.rmit.s3269999.ProductLocal findByPrimaryKey(java.lang.Long id) throws FinderException;

    java.util.Collection findAll() throws FinderException;

    vn.edu.rmit.s3269999.ProductLocal create(vn.edu.rmit.s3269999.dto.ProductDTO product) throws CreateException;
}
