package vn.com.onlinebookstore;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import vn.com.onlinebookstore.dto.OrderLineDTO;

public interface OrderLineLocalHome extends EJBLocalHome {

    vn.com.onlinebookstore.OrderLineLocal findByPrimaryKey(java.lang.Long id) throws FinderException;

    vn.com.onlinebookstore.OrderLineLocal create(OrderLineDTO orderLine) throws CreateException;
}
