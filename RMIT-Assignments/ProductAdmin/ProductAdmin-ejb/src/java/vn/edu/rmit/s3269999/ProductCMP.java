package vn.edu.rmit.s3269999;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.ejb.CreateException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public abstract class ProductCMP implements EntityBean {

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
    public abstract Integer getProductID();

    public abstract void setProductID(Integer productID);

    public abstract String getProductName();

    public abstract void setProductName(String productName);

    public abstract Double getProductPrice();

    public abstract void setProductPrice(Double productPrice);

    public Integer ejbCreate(ProductDT product) throws CreateException {
        Integer productID = null;
        try {
            InitialContext ic = new InitialContext();
            DataSource dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/cosc2465");

            Connection connection = dataSource.getConnection();
            PreparedStatement query = connection.prepareStatement("select max(id) from lab2_products");
            ResultSet result = query.executeQuery();

            if (result.next()) {
                productID = new Integer(result.getInt(1) + 1);
            } else {
                productID = new Integer(1);
            }

            setProductID(productID);
            setProductName(product.getProductName());
            setProductPrice(product.getProductPrice());

            result.close();
            query.close();
            connection.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return productID;
    }

    public void ejbPostCreate(ProductDT product) {
        // TODO populate relationships here if appropriate
    }

    public int ejbHomeGetNextID() {
        if (getProductID() == null) {
            return 1;
        }
        return getProductID().intValue() + 1;
    }
}
