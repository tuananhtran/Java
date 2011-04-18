package vn.edu.rmit.s3269999;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import vn.edu.rmit.s3269999.dto.ProductDTO;

public interface ProductLocalHome extends EJBLocalHome {

    vn.edu.rmit.s3269999.ProductLocal findByPrimaryKey(Long id) throws FinderException;

    java.util.Collection findAllOrderedByName() throws FinderException;

    vn.edu.rmit.s3269999.ProductLocal create(ProductDTO product) throws CreateException;
}
