package vn.com.onlinebookstore;

import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import vn.com.onlinebookstore.dto.CategoryDTO;

public abstract class Category implements EntityBean {

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
    //accessor for category id
    public abstract Long getId();

    //mutator for category id
    public abstract void setId(Long id);

    //accessor for category name
    public abstract String getName();

    //mutator for category name
    public abstract void setName(String name);

    //accessor for sort order
    public abstract Integer getSortOrder();

    //mutator for sort order
    public abstract void setSortOrder(Integer sortOrder);

    //accessor for CMR field books
    public abstract Collection getBooks();

    //mutator for CMR field books
    public abstract void setBooks(Collection books);

    public java.lang.Long ejbCreate(CategoryDTO category) throws CreateException, Exception {
        // TODO add additional validation code, throw CreateException if data is not valid

        //set entity fields with provided data
        setId(vn.com.onlinebookstore.util.Utils.getNextId("Category"));
        setName(category.getName());
        setSortOrder(category.getSortOrder());

        return null;
    }

    public void ejbPostCreate(CategoryDTO category) {
        // TODO populate relationships here if appropriate
    }
}
