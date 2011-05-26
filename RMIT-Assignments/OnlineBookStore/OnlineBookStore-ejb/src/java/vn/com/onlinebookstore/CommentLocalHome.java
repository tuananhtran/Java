package vn.com.onlinebookstore;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import vn.com.onlinebookstore.dto.CommentDTO;

public interface CommentLocalHome extends EJBLocalHome {

    vn.com.onlinebookstore.CommentLocal findByPrimaryKey(java.lang.Long id) throws FinderException;

    vn.com.onlinebookstore.CommentLocal findByUserIdAndBookId(java.lang.Long userId, java.lang.Long bookId) throws FinderException;

    java.util.Collection findAllByBookId(java.lang.Long bookId) throws FinderException;

    vn.com.onlinebookstore.CommentLocal create(CommentDTO comment) throws CreateException;
}
