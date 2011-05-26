package vn.com.onlinebookstore;

import java.util.Date;
import javax.ejb.EJBLocalObject;

public interface CommentLocal extends EJBLocalObject {

    Long getId();

    void setId(Long id);

    Integer getRating();

    void setRating(Integer rating);

    String getContent();

    void setContent(String content);

    Date getDate();

    void setDate(Date date);

    BookLocal getBook();

    void setBook(BookLocal book);

    UserLocal getUser();

    void setUser(UserLocal user);
}
