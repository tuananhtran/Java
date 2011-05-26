package vn.com.onlinebookstore;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import vn.com.onlinebookstore.dto.CategoryDTO;

public interface CategoryLocalHome extends EJBLocalHome {

    vn.com.onlinebookstore.CategoryLocal findByPrimaryKey(java.lang.Long id) throws FinderException;

    java.util.Collection findAll() throws FinderException;

    java.util.Collection findAllOrderedByName() throws FinderException;

    vn.com.onlinebookstore.CategoryLocal create(CategoryDTO category) throws CreateException;
}
