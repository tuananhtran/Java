package vn.edu.rmit.s3269999;

import java.util.Collection;
import java.util.Iterator;
import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import vn.edu.rmit.s3269999.dto.ProductDTO;

public class ProductAdmin implements SessionBean {

    private SessionContext context;
    private ProductLocalHome productManager = null;

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
    public void ejbCreate() throws NamingException {
        // TODO implement ejbCreate if necessary, acquire resources
        // This method has access to the JNDI context so resource aquisition
        // spanning all methods can be performed here such as home interfaces
        // and data sources.

        InitialContext ic = new InitialContext();
        productManager = (ProductLocalHome) ic.lookup("java:comp/env/ejb/Product");
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")

    public void addProduct(ProductDTO product) throws CreateException {
        productManager.create(product);
    }

    public ProductDTO getProduct(Long id) throws FinderException {
        ProductLocal productLocal = productManager.findByPrimaryKey(id);

        ProductDTO product = new ProductDTO();
        product.setId(id);
        product.setName(productLocal.getName());
        product.setPrice(productLocal.getPrice());

        return product;
    }

    public Collection getAllProducts() throws FinderException {
        Collection productLocals = productManager.findAllOrderedByName();
        Collection products = new java.util.ArrayList();

        Iterator iterator = productLocals.iterator();
        while (iterator.hasNext()) {
            ProductLocal productLocal = (ProductLocal) iterator.next();

            ProductDTO product = new ProductDTO();
            product.setId(productLocal.getId());
            product.setName(productLocal.getName());
            product.setPrice(productLocal.getPrice());

            products.add(product);
        }

        return products;
    }
}
