package vn.com.onlinebookstore;

import java.util.Collection;
import java.util.Date;
import javax.ejb.CreateException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import vn.com.onlinebookstore.dto.UserDTO;

public abstract class User implements EntityBean {

    private EntityContext context;

    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click on the + sign on the left to edit the code.">
    // TODO Consider creating Transfer Object to encapsulate data
    // TODO Review finder methods
    /**
     * @see javax.ejb.EntityBean#setEntityContext(javax.ejb.EntityContext)
     */
    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }

    /**
     * @see javax.ejb.EntityBean#ejbActivate()
     */
    public void ejbActivate() {
    }

    /**
     * @see javax.ejb.EntityBean#ejbPassivate()
     */
    public void ejbPassivate() {
    }

    /**
     * @see javax.ejb.EntityBean#ejbRemove()
     */
    public void ejbRemove() {
    }

    /**
     * @see javax.ejb.EntityBean#unsetEntityContext()
     */
    public void unsetEntityContext() {
        context = null;
    }

    /**
     * @see javax.ejb.EntityBean#ejbLoad()
     */
    public void ejbLoad() {
    }

    /**
     * @see javax.ejb.EntityBean#ejbStore()
     */
    public void ejbStore() {
    }

    // </editor-fold>
    //accessor for id
    public abstract Long getId();

    //mutator for id
    public abstract void setId(Long id);

    //accessor for login name
    public abstract String getLogin();

    //mutator for login name
    public abstract void setLogin(String login);

    //accessor for email address
    public abstract String getEmail();

    //mutator for email address
    public abstract void setEmail(String email);

    //accessor for password
    public abstract String getPassword();

    //mutator for password
    public abstract void setPassword(String password);

    //accessor for phone number
    public abstract Integer getPhone();

    //mutator for phone number
    public abstract void setPhone(Integer phone);

    //accessor for full name
    public abstract String getName();

    //mutator for full name
    public abstract void setName(String name);

    //accessor for birthday
    public abstract Date getBirthday();

    //mutator for birthday
    public abstract void setBirthday(Date birthday);

    //accessor for account creation date
    public abstract Date getDateCreated();

    //mutator for account creation date
    public abstract void setDateCreated(Date dateCreated);

    //accessor for CMR field orders
    public abstract Collection getOrders();

    //mutator for CMR field orders
    public abstract void setOrders(Collection orders);

    //accessor for CMR field comments
    public abstract Collection getComments();

    //mutator for CMR field comments
    public abstract void setComments(Collection comments);

    public java.lang.Long ejbCreate(UserDTO user) throws CreateException, Exception {
        // TODO add additional validation code, throw CreateException if data is not valid

        //set entity fields with provided data
        setId(vn.com.onlinebookstore.util.Utils.getNextId("Users"));
        setLogin(user.getLogin());
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        setPhone(user.getPhone());
        setName(user.getName());
        setBirthday(user.getBirthday());
        setDateCreated(new Date());

        return null;
    }

    public void ejbPostCreate(UserDTO user) {
        // TODO populate relationships here if appropriate
    }
}
