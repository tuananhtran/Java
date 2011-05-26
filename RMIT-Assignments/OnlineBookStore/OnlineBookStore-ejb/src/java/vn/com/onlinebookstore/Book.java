package vn.com.onlinebookstore;

import java.util.Collection;
import java.util.Date;
import javax.ejb.CreateException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import vn.com.onlinebookstore.dto.BookDTO;

public abstract class Book implements EntityBean {

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

    //accessor for title
    public abstract String getTitle();

    //mutator for title
    public abstract void setTitle(String title);

    //accessor for description
    public abstract String getDescription();

    //mutator for description
    public abstract void setDescription(String description);

    //accessor for author name
    public abstract String getAuthor();

    //mutator for author name
    public abstract void setAuthor(String author);

    //accessor for publisher name
    public abstract String getPublisher();

    //mutator for publisher name
    public abstract void setPublisher(String publisher);

    //accessor for publishing date
    public abstract Date getDatePublished();

    //mutator for publishing date
    public abstract void setDatePublished(Date datePublished);

    //accessor for unit price
    public abstract Double getPrice();

    //mutator for unit price
    public abstract void setPrice(Double price);

    //accessor for photo url
    public abstract String getPhoto();

    //mutator for photo url
    public abstract void setPhoto(String photo);

    //accessor for total rating value
    public abstract Long getRatingValue();

    //mutator for total rating value
    public abstract void setRatingValue(Long ratingValue);

    //accessor for total rating count
    public abstract Long getRatingCount();

    //mutator for total rating count
    public abstract void setRatingCount(Long ratingCount);

    //accessor for CMR field comments
    public abstract Collection getComments();

    //mutator for CMR field comments
    public abstract void setComments(Collection comments);

    //accessor for CMR field category
    public abstract CategoryLocal getCategory();

    //mutator for CMR field category
    public abstract void setCategory(CategoryLocal category);

    //accessor for CMR field orderLines
    public abstract Collection getOrderLines();

    //mutator for CMR field order lines
    public abstract void setOrderLines(Collection orderLines);

    public java.lang.Long ejbCreate(BookDTO book) throws CreateException, Exception {
        // TODO add additional validation code, throw CreateException if data is not valid

        //set entity fields with provided data
        setId(vn.com.onlinebookstore.util.Utils.getNextId("Book"));
        setTitle(book.getTitle());
        setDescription(book.getDescription());
        setAuthor(book.getAuthor());
        setPublisher(book.getPublisher());
        setDatePublished(book.getDatePublished());
        setPrice(book.getPrice());
        setRatingValue(book.getRatingValue());
        setRatingCount(book.getRatingCount());

        return null;
    }

    public void ejbPostCreate(BookDTO book) {
        // TODO populate relationships here if appropriate
    }
}
