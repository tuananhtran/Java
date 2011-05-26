package vn.com.onlinebookstore;

import javax.ejb.CreateException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import vn.com.onlinebookstore.dto.OrderLineDTO;

public abstract class OrderLine implements EntityBean {

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

    //accessor for unit price
    public abstract Double getUnitPrice();

    //mutator for unit price
    public abstract void setUnitPrice(Double unitPrice);

    //accessor for quantity
    public abstract Long getQuantity();

    //mutator for quantity
    public abstract void setQuantity(Long quantity);

    //accessor for CMR field order
    public abstract OrderLocal getOrder();

    //mutator for CMR field order
    public abstract void setOrder(OrderLocal order);

    //accessor for CMR field book
    public abstract BookLocal getBook();

    //mutator for CMR field book
    public abstract void setBook(BookLocal book);

    public java.lang.Long ejbCreate(OrderLineDTO orderLine) throws CreateException, Exception {
        // TODO add additional validation code, throw CreateException if data is not valid

        //set entity fields with provided data
        setId(vn.com.onlinebookstore.util.Utils.getNextId("Order_Line"));
        setUnitPrice(orderLine.getUnitPrice());
        setQuantity(orderLine.getQuantity());
        return null;
    }

    public void ejbPostCreate(OrderLineDTO orderLine) {
        // TODO populate relationships here if appropriate
    }
}
