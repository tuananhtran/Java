package vn.com.onlinebookstore;

import java.util.Date;
import javax.ejb.CreateException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import vn.com.onlinebookstore.dto.CommentDTO;

public abstract class Comment implements EntityBean {

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

    //accessor for rating
    public abstract Integer getRating();

    //mutator for rating
    public abstract void setRating(Integer rating);

    //accessor for content
    public abstract String getContent();

    //mutator for content
    public abstract void setContent(String content);

    //accessor for comment date
    public abstract Date getDate();

    //mutator for comment date
    public abstract void setDate(Date date);

    //accessor for CMR field book
    public abstract BookLocal getBook();

    //mutator for CMR field book
    public abstract void setBook(BookLocal book);

    //accessor for CMR field user
    public abstract UserLocal getUser();

    //mutator for CMR field user
    public abstract void setUser(UserLocal user);

    public java.lang.Long ejbCreate(CommentDTO comment) throws CreateException, Exception {
        // TODO add additional validation code, throw CreateException if data is not valid

        //set entity fields with provided data
        setId(vn.com.onlinebookstore.util.Utils.getNextId("Comment"));
        setRating(comment.getRating());
        setContent(comment.getContent());
        setDate(new Date());

        return null;
    }

    public void ejbPostCreate(CommentDTO comment) {
        // TODO populate relationships here if appropriate
    }
}
