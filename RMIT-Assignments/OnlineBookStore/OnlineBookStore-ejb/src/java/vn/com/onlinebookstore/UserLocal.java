package vn.com.onlinebookstore;

import java.util.Collection;
import java.util.Date;
import javax.ejb.EJBLocalObject;

public interface UserLocal extends EJBLocalObject {

    Long getId();

    void setId(Long id);

    String getLogin();

    void setLogin(String login);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    Integer getPhone();

    void setPhone(Integer phone);

    String getName();

    void setName(String name);

    Date getBirthday();

    void setBirthday(Date birthday);

    Date getDateCreated();

    void setDateCreated(Date dateCreated);

    Collection getOrders();

    void setOrders(Collection orders);

    Collection getComments();

    void setComments(Collection comments);
}
