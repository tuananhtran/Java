package vn.com.onlinebookstore;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import vn.com.onlinebookstore.dto.UserDTO;

public interface UserLocalHome extends EJBLocalHome {

    vn.com.onlinebookstore.UserLocal findByPrimaryKey(java.lang.Long id) throws FinderException;

    vn.com.onlinebookstore.UserLocal findByLoginDetails(java.lang.String login, String password) throws FinderException;

    vn.com.onlinebookstore.UserLocal findByLogin(java.lang.String login) throws FinderException;

    vn.com.onlinebookstore.UserLocal findByEmail(java.lang.String email) throws FinderException;

    vn.com.onlinebookstore.UserLocal create(UserDTO user) throws CreateException;
}
