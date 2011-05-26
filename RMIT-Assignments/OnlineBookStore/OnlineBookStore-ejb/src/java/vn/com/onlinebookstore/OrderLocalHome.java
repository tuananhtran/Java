package vn.com.onlinebookstore;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import vn.com.onlinebookstore.dto.OrderDTO;

public interface OrderLocalHome extends EJBLocalHome {

    vn.com.onlinebookstore.OrderLocal findByPrimaryKey(java.lang.Long id) throws FinderException;

    vn.com.onlinebookstore.OrderLocal create(OrderDTO order) throws CreateException;
}
