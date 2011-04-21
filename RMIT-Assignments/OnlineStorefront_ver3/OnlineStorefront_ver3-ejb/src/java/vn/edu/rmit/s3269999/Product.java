package vn.edu.rmit.s3269999;

import javax.ejb.CreateException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import vn.edu.rmit.s3269999.dto.ProductDTO;
import vn.edu.rmit.s3269999.utils.DatabaseUtility;

public abstract class Product implements EntityBean {

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
    public abstract Long getId();

    public abstract void setId(Long id);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract Double getPrice();

    public abstract void setPrice(Double price);

    public java.lang.Long ejbCreate(ProductDTO product) throws CreateException {
//        if (key == null) {
//            throw new CreateException("The field \"key\" must not be null");
//        }
//
//        // TODO add additional validation code, throw CreateException if data is not valid
//        setPk(key);
//
//        return null;

        if (product == null) {
            throw new CreateException("Cannot add product to database! Null Product DTO object!");
        }

        setId(DatabaseUtility.getNextId("Product"));
        setName(product.getName());
        setPrice(product.getPrice());

        return null;
    }

    public void ejbPostCreate(ProductDTO product) {
        // TODO populate relationships here if appropriate
    }
}
