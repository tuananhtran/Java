package vn.com.onlinebookstore;

import java.util.Collection;
import java.util.Date;
import javax.ejb.EJBLocalObject;

public interface OrderLocal extends EJBLocalObject {

    Long getId();

    void setId(Long id);

    Date getDate();

    void setDate(Date date);

    Double getAmount();

    void setAmount(Double amount);

    String getAddress();

    void setAddress(String address);

    Collection getOrderLines();

    void setOrderLines(Collection orderLines);

    UserLocal getUser();

    void setUser(UserLocal user);
}
