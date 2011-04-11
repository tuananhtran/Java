package vn.edu.rmit.s3269999;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;

public class ProductAdmin implements SessionBean {

    private SessionContext context;
    private ProductCMPLocalHome productFactory;

    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click the + sign on the left to edit the code.">;
    // TODO Add code to acquire and use other enterprise resources (DataSource, JMS, enterprise bean, Web services)
    // TODO Add business methods or web service operations
    /**
     * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
     */
    public void setSessionContext(SessionContext aContext) {
        context = aContext;
    }

    /**
     * @see javax.ejb.SessionBean#ejbActivate()
     */
    public void ejbActivate() {
    }

    /**
     * @see javax.ejb.SessionBean#ejbPassivate()
     */
    public void ejbPassivate() {
    }

    /**
     * @see javax.ejb.SessionBean#ejbRemove()
     */
    public void ejbRemove() {
    }

    // </editor-fold>;
    /**
     * See section 7.10.3 of the EJB 2.0 specification
     * See section 7.11.3 of the EJB 2.1 specification
     */
    public void ejbCreate() {
        // TODO implement ejbCreate if necessary, acquire resources
        // This method has access to the JNDI context so resource aquisition
        // spanning all methods can be performed here such as home interfaces
        // and data sources.
        try {
            InitialContext ic = new InitialContext();
            productFactory = (ProductCMPLocalHome) ic.lookup(
                    "java:comp/env/ejb/ProductCMPLocalHome");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")

    public int addProduct(ProductDT product) {
        ProductCMPLocal newProduct = null;

        try {
            newProduct = productFactory.create(product);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        return newProduct.getProductID().intValue();
    }

    public ProductDT getProductByID(int productID) {
        ProductDT product = null;

        try {
            ProductCMPLocal productLocal = productFactory.findByPrimaryKey(new Integer(productID));

            product = new ProductDT();
            product.setProductID(productLocal.getProductID());
            product.setProductName(productLocal.getProductName());
            product.setProductPrice(productLocal.getProductPrice());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        return product;
    }

    public void deleteProduct(int productID) {
        try {
            productFactory.findByPrimaryKey(new Integer(productID)).remove();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
