package vn.com.onlinebookstore;

import java.util.Collection;
import java.util.Date;
import javax.ejb.CreateException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import vn.com.onlinebookstore.dto.OrderDTO;

public abstract class Order implements EntityBean {

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

    //accessor for order date
    public abstract Date getDate();

    //mutator for order date
    public abstract void setDate(Date date);

    //accessor for order total amount
    public abstract Double getAmount();

    //mutator for order total amount
    public abstract void setAmount(Double amount);

    //accessor for shipping address
    public abstract String getAddress();

    //mutator for shipping address
    public abstract void setAddress(String address);

    //accessor for CMR field orderLines
    public abstract Collection getOrderLines();

    //mutator for CMR field orderLines
    public abstract void setOrderLines(Collection orderLines);

    //accessor for CMR field user
    public abstract UserLocal getUser();

    //mutator for CMR field user
    public abstract void setUser(UserLocal user);

    public java.lang.Long ejbCreate(OrderDTO order) throws CreateException, Exception {
        // TODO add additional validation code, throw CreateException if data is not valid

        //set entity fields with provided data
        setId(vn.com.onlinebookstore.util.Utils.getNextId("Orders"));
        setAmount(order.getAmount());
        setAddress(order.getAddress());
        setDate(new Date());

        return null;
    }

    public void ejbPostCreate(OrderDTO order) {
        // TODO populate relationships here if appropriate
    }
}
