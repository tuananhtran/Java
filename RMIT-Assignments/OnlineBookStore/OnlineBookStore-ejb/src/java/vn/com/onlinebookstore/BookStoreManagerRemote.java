package vn.com.onlinebookstore;

import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.ejb.EJBObject;
import vn.com.onlinebookstore.dto.*;

public interface BookStoreManagerRemote extends EJBObject {

    void register(UserDTO user) throws RemoteException;

    void login(java.lang.String username, java.lang.String password) throws RemoteException;

    UserDTO getCurrentUser() throws RemoteException;

    void updateProfile(UserDTO user) throws RemoteException;

    CategoryDTO getCategory(java.lang.Long categoryId) throws RemoteException;

    ArrayList browseBooks(java.lang.Long categoryId, java.lang.Integer pageSize) throws RemoteException;

    ArrayList searchBooks(java.lang.String title, java.lang.String author, java.lang.Long categoryId, java.lang.Integer pageSize) throws RemoteException;

    BookDTO getBook(java.lang.Long id) throws RemoteException;

    ArrayList getBookComments(java.lang.Long bookID) throws RemoteException;

    ArrayList getUserNamesByBookComments(java.lang.Long bookID) throws RemoteException;

    void addComment(CommentDTO comment) throws RemoteException;

    void addToCart(java.lang.Long bookId) throws RemoteException;

    void updateCart(java.util.ArrayList cart) throws RemoteException;

    void removeFromCart(java.lang.Long bookId) throws RemoteException;

    void modifyCart(java.lang.Long bookId, java.lang.Long quantity) throws RemoteException;

    ArrayList getCart() throws RemoteException;

    Double getCartValue() throws RemoteException;

    void clearCart() throws RemoteException;

    void checkOut(java.lang.String address) throws RemoteException;

    void confirmOrder(java.lang.Boolean confirmation) throws RemoteException;

    ArrayList getPastOrders() throws RemoteException;

    ArrayList getPastOrderItems() throws RemoteException;

    ArrayList getAllCategories() throws RemoteException;
}
