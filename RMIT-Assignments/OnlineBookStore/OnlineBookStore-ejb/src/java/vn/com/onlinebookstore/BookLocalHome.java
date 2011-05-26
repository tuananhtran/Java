package vn.com.onlinebookstore;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import vn.com.onlinebookstore.dto.BookDTO;

public interface BookLocalHome extends EJBLocalHome {

    vn.com.onlinebookstore.BookLocal findByPrimaryKey(java.lang.Long id) throws FinderException;

    java.util.Collection findBooksByTitleAndAuthorAndCategoryId(java.lang.String title, java.lang.String author, java.lang.Long categoryId) throws FinderException;

    java.util.Collection findBooksByTitleAndCategoryId(java.lang.String title, java.lang.Long categoryId) throws FinderException;

    java.util.Collection findBooksByAuthorAndCategoryId(java.lang.String author, java.lang.Long categoryId) throws FinderException;

    java.util.Collection findBooksByCategoryId(java.lang.Long categoryId) throws FinderException;

    vn.com.onlinebookstore.BookLocal create(BookDTO book) throws CreateException;
}
