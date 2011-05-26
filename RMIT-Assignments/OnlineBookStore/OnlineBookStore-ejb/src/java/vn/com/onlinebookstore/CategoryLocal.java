package vn.com.onlinebookstore;

import java.util.Collection;
import javax.ejb.EJBLocalObject;

public interface CategoryLocal extends EJBLocalObject {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    Integer getSortOrder();

    void setSortOrder(Integer sortOrder);

    Collection getBooks();

    void setBooks(Collection books);
}
