package vn.com.onlinebookstore;

import java.util.Collection;
import java.util.Date;
import javax.ejb.EJBLocalObject;

public interface BookLocal extends EJBLocalObject {

    Long getId();

    void setId(Long id);

    String getTitle();

    void setTitle(String title);

    String getDescription();

    void setDescription(String description);

    String getAuthor();

    void setAuthor(String author);

    String getPublisher();

    void setPublisher(String publisher);

    Date getDatePublished();

    void setDatePublished(Date datePublished);

    Double getPrice();

    void setPrice(Double price);

    String getPhoto();

    void setPhoto(String photo);

    Long getRatingValue();

    void setRatingValue(Long ratingValue);

    Long getRatingCount();

    void setRatingCount(Long ratingCount);

    Collection getComments();

    void setComments(Collection comments);

    CategoryLocal getCategory();

    void setCategory(CategoryLocal category);

    Collection getOrderLines();

    void setOrderLines(Collection orderLines);
}
