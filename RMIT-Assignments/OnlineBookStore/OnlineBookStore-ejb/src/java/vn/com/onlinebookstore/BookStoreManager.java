package vn.com.onlinebookstore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import vn.com.onlinebookstore.dto.*;
import vn.com.onlinebookstore.util.Utils;

public class BookStoreManager implements SessionBean {

    private SessionContext context;
    //declare all entity managers
    private BookLocalHome bookManager = null;
    private CategoryLocalHome categoryManager = null;
    private CommentLocalHome commentManager = null;
    private OrderLocalHome orderManager = null;
    private OrderLineLocalHome orderLineManager = null;
    private UserLocalHome userManager = null;
    //declare new shopping cart
    private ArrayList cart = null;
    //declare new order
    private OrderDTO order = null;
    //declare current user
    private UserLocal user = null;

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
    public void ejbCreate() throws CreateException, Exception {
        // TODO implement ejbCreate if necessary, acquire resources
        // This method has access to the JNDI context so resource aquisition
        // spanning all methods can be performed here such as home interfaces
        // and data sources.

        //get initial context
        InitialContext ic = new InitialContext();

        //initialize all entity managers
        bookManager = (BookLocalHome) ic.lookup("java:comp/env/ejb/BookLocalHome");
        categoryManager = (CategoryLocalHome) ic.lookup("java:comp/env/ejb/CategoryLocalHome");
        commentManager = (CommentLocalHome) ic.lookup("java:comp/env/ejb/CommentLocalHome");
        orderManager = (OrderLocalHome) ic.lookup("java:comp/env/ejb/OrderLocalHome");
        orderLineManager = (OrderLineLocalHome) ic.lookup("java:comp/env/ejb/OrderLineLocalHome");
        userManager = (UserLocalHome) ic.lookup("java:comp/env/ejb/UserLocalHome");

        //initialize order
        order = new OrderDTO();

        //initialize shopping cart
        cart = new ArrayList();

    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")

    //register new user
    public void register(UserDTO user) throws Exception {

        //if user already logged in, throw new error
        if (this.user != null) {
            throw new Exception("Cannot register! Only anonymous users can create new account!");
        }

        //if username is empty, throw an error
        if (user.getLogin() == null || user.getLogin().isEmpty()) {
            throw new Exception("Username cannot be blank!");
        } else {

            //get user by username
            UserLocal userLocal = null;
            try {
                userLocal = userManager.findByLogin(user.getLogin());
            } catch (Exception e) {
                if (user.getEmail() == null || user.getEmail().isEmpty()) {
                    //if email address is empty, throw an error
                    throw new Exception("Email address cannot be blank!");
                } else {
                    //get user by email address
                    try {
                        userLocal = userManager.findByEmail(user.getEmail());
                    } catch (Exception ex) {
                        if (user.getPassword() == null || user.getPassword().isEmpty()) {
                            //if password is empty, throw an error
                            throw new Exception("Password cannot be blank!");
                        } else {
                            //else, create new user, add to database and update current session's user
                            this.user = userManager.create(user);
                            return;
                        }
                    }
                    //if email existed, throw an error
                    throw new Exception("Email address already existed in database!");
                }
            }
            //if username existed, throw an error
            throw new Exception("Username already existed!");
        }

    }

    //login user to the system
    public void login(java.lang.String username, java.lang.String password) throws Exception {

        //if user already logged in, throw an error
        if (user != null) {
            throw new Exception("User already logged in!");
        }

        try {
            //check if user existed
            userManager.findByLogin(username);
        } catch (Exception e) {
            //if user not existed, throw an error message
            throw new Exception("User not existed!");
        }

        try {
            //if user existed, log that user in
            user = userManager.findByLoginDetails(username, password);
        } catch (Exception e) {
            //if user not logged in, throw an error
            throw new Exception("Invalid username/password!");
        }

    }

    //update user personal information
    public void updateProfile(UserDTO user) throws Exception {

        //if user not logged in, throw an error
        if (this.user == null) {
            throw new Exception("Only logged in users can do this!");
        } else if (!user.getLogin().equals(this.user.getLogin())) {
            //if wrong username provided, throw an error
            throw new Exception("Incorrect username!");
        } else if (user.getPassword().isEmpty()) {
            //if password is blank, throw an error
            throw new Exception("Password cannot be blank!");
        } else if (user.getEmail().isEmpty()) {
            //if email is blank, throw an error
            throw new Exception("Email cannot be blank!");
        } else {
            //find user for specified email address
            UserLocal userLocal = null;
            try {
                userLocal = userManager.findByEmail(user.getEmail());
            } catch (Exception e) {
                //if email is not in database, update current user information
                this.user.setEmail(user.getEmail());
                this.user.setPassword(user.getPassword());
                this.user.setPhone(user.getPhone());
                this.user.setName(user.getName());
                this.user.setBirthday(user.getBirthday());
            }

            //if email already in database, throw an error
            throw new Exception("Email already in database!");
        }

    }

    //return current user object
    public UserDTO getCurrentUser() {

        //if user logged in, return user DTO object with current user data
        if (user != null) {
            //create new user DTO object
            UserDTO userDTO = new UserDTO();

            //set all fields with provided data
            userDTO.setId(user.getId());
            userDTO.setLogin(user.getLogin());
            userDTO.setEmail(user.getEmail());
            userDTO.setPassword(user.getPassword());
            userDTO.setPhone(user.getPhone());
            userDTO.setName(user.getName());
            userDTO.setBirthday(user.getBirthday());
            userDTO.setDateCreated(user.getDateCreated());

            //return DTO object
            return userDTO;
        }

        //if user not logged in, return null
        return null;
    }

    //get category with specified id
    public CategoryDTO getCategory(java.lang.Long categoryId) throws Exception {

        //if category is null, return null
        if (categoryId == null) {
            return null;
        }

        //get local object
        CategoryLocal categoryLocal = categoryManager.findByPrimaryKey(categoryId);

        //convert to data transfer object
        CategoryDTO categoryDTO = Utils.convertToCategoryDTO(categoryLocal);

        //return DTO object
        return categoryDTO;
    }

    //browse for books by categories
    public ArrayList browseBooks(java.lang.Long categoryId, java.lang.Integer pageSize) throws Exception {

        //initialize temp and output variables
        ArrayList books = new ArrayList(); //output book collection
        Collection tempBooks = null;
        ArrayList pageBooks = new ArrayList();    //collection to store books per page
        Iterator iterator = null;   //iterator to loop through collection(s)
        int counter = 0;    //counter to count number of items processed for each colllection

        //if category id provided, find book with specified category
        if (categoryId != null) {
            tempBooks = bookManager.findBooksByCategoryId(categoryId);
        } else {
            //else, find all books for all categories (order by sort order)
            Collection categories = categoryManager.findAll();

            //setup tempBooks collection
            tempBooks = new ArrayList();

            //get iterator
            iterator = categories.iterator();

            //add books to temp collection for each category in the collection
            while (iterator.hasNext()) {
                //get category object
                CategoryLocal category = (CategoryLocal) iterator.next();

                //get all books for that category
                Collection booksByCategory = category.getBooks();

                //add to search result
                tempBooks.addAll(booksByCategory);
            }
        }

        //get iterator for temp books collection
        iterator = tempBooks.iterator();
        books = new ArrayList();

        //transform each book local object into book DTO and store in a new collection
        while (iterator.hasNext()) {

            //get currently pointed book local item
            BookLocal bookLocal = (BookLocal) iterator.next();

            //add DTO object to collection
            pageBooks.add(Utils.convertToBookDTO(bookLocal));

            //increment counter by 1
            counter++;

            //if page size limit reached, continue to add to new page
            if (!iterator.hasNext() || counter == pageSize.intValue()) {
                //add current collection to result
                books.add(pageBooks);

                //create new collection
                pageBooks = new ArrayList();

                //reset counter
                counter = 0;
            }

        }

        //return output collection
        return books;

    }

    //search for book with title, author name and category id (optional)
    public ArrayList searchBooks(java.lang.String title, java.lang.String author, java.lang.Long categoryId, java.lang.Integer pageSize) throws Exception {

        //if page size is less than 1, return null
        if (pageSize.intValue() < 1) {
            return null;
        }

        //initialize output and temp variables
        Iterator iterator = null;   //iterator object
        ArrayList foundBooks = new ArrayList();    //found books collection
        ArrayList books = null; //output book collection
        ArrayList pageBooks = new ArrayList();    //temp collection to store books in a page
        int counter = 0;    //counter to count number of items in a page (for pagination)

        //if no category id provided
        if (categoryId == null) {
            //setup iterator of category collection
            Collection allCategories = categoryManager.findAll();
            iterator = allCategories.iterator();

            //find and add books with specified fields by category id (sorted by sort order)
            while (iterator.hasNext()) {
                //get category id
                Long id = ((CategoryLocal) iterator.next()).getId();

                //if title and author not provided, find book with only category
                if ((title == null || title.isEmpty()) && (author == null || author.isEmpty())) {
                    foundBooks.addAll(bookManager.findBooksByCategoryId(id));
                } else if (title == null || title.isEmpty()) {
                    //else if author is provided, add author to search condition
                    foundBooks.addAll(bookManager.findBooksByAuthorAndCategoryId(author, id));
                } else if (author == null || author.isEmpty()) {
                    //else if title is provided, add title to search condition
                    foundBooks.addAll(bookManager.findBooksByTitleAndCategoryId(title, id));
                } else {
                    //else, add all to search condition
                    foundBooks.addAll(bookManager.findBooksByTitleAndAuthorAndCategoryId(title, author, id));
                }
            }
        } else {
            //if category id provided, find with provided fields

            //if title and author not provided, find book with only category
            if ((title == null || title.isEmpty()) && (author == null || author.isEmpty())) {
                foundBooks.addAll(bookManager.findBooksByCategoryId(categoryId));
            } else if (title == null || title.isEmpty()) {
                //else if author is provided, add author to search condition
                foundBooks.addAll(bookManager.findBooksByAuthorAndCategoryId(author, categoryId));
            } else if (author == null || author.isEmpty()) {
                //else if title is provided, add title to search condition
                foundBooks.addAll(bookManager.findBooksByTitleAndCategoryId(title, categoryId));
            } else {
                //else, add all to search condition
                foundBooks.addAll(bookManager.findBooksByTitleAndAuthorAndCategoryId(title, author, categoryId));
            }
        }

        //setup iterator and output collection
        iterator = foundBooks.iterator();
        books = new ArrayList();

        //transform each book local object into book DTO and store in a new collection
        while (iterator.hasNext()) {

            //get currently pointed book local item
            BookLocal bookLocal = (BookLocal) iterator.next();

            //add DTO object to collection
            pageBooks.add(Utils.convertToBookDTO(bookLocal));

            //increment counter by 1
            counter++;

            //if page size limit reached, continue to add to new page
            if (!iterator.hasNext() || counter == pageSize.intValue()) {
                //add current collection to result
                books.add(pageBooks);

                //create new collection
                pageBooks = new ArrayList();

                //reset counter
                counter = 0;
            }
        }

        return books;

    }

    //get book with specified id
    public BookDTO getBook(Long id) throws Exception {

        //get book local object
        BookLocal bookLocal = bookManager.findByPrimaryKey(id);

        //convert found book to DTO object and return
        return Utils.convertToBookDTO(bookLocal);

    }

    //get all user names by book comments
    public ArrayList getUserNamesByBookComments(java.lang.Long bookID) throws Exception {

        //get comment collection for specified book
        Collection bookComments = commentManager.findAllByBookId(bookID);

        //declare temp variables
        Iterator iterator = bookComments.iterator();   //iterator to loop through comment collection
        CommentLocal commentLocal = null;   //local comment object

        //declare output variable
        ArrayList userNames = new ArrayList();

        //get user name from each user object and store in a new collection
        while (iterator.hasNext()) {
            //get currently pointed comment local item
            commentLocal = (CommentLocal) iterator.next();

            //get user for specified comment
            UserLocal userLocal = commentLocal.getUser();

            //add full name to collection
            userNames.add(userLocal.getName());
        }

        //return comment collection
        return userNames;

    }

    //get all comments of a book
    public ArrayList getBookComments(Long bookID) throws Exception {

        //get comment collection for specified book
        Collection bookComments = commentManager.findAllByBookId(bookID);

        //declare temp variables
        Iterator iterator = bookComments.iterator();   //iterator to loop through comment collection
        CommentLocal commentLocal = null;   //local object
        CommentDTO commentDTO = null;   //data transfer object

        //declare output variable
        ArrayList comments = new ArrayList();

        //transform each comment local object into comment DTO and store in a new collection
        while (iterator.hasNext()) {
            //get currently pointed comment local item
            commentLocal = (CommentLocal) iterator.next();

            //convert to DTO object
            commentDTO = Utils.convertToCommentDTO(commentLocal);

            //add DTO object to collection
            comments.add(commentDTO);
        }

        //return comment collection
        return comments;

    }

    //add rating and comment too a book
    public void addComment(CommentDTO comment) throws Exception {

        //if user is not logged in, throw an error
        if (user == null) {
            throw new Exception("User is not logged in!");
        } else if (!comment.getUserId().equals(user.getId())) {
            //if user id of comment is different from current user's id, throw an error
            throw new Exception("Only comments made by this user accepted!");
        } else if (comment.getRating().intValue() < 1 || comment.getRating().intValue() > 5) {
            //if rating is out of range from 1 to 5, throw an error
            throw new Exception("Rating must be from 1 to 5!");
        } else {
            //check if comment already existed
            try {
                commentManager.findByUserIdAndBookId(comment.getUserId(), comment.getBookId());
            } catch (Exception e) {
                //get book local object by its id
                BookLocal bookLocal = bookManager.findByPrimaryKey(comment.getBookId());

                //create new comment in database
                CommentLocal commentLocal = commentManager.create(comment);

                //set user for comment
                commentLocal.setUser(user);

                //set book for comment
                commentLocal.setBook(bookLocal);

                return;
            }

            //if comment already existed, throw an error
            throw new Exception("Each user can only comment a book once!");
        }

    }

    //add a book to shopping cart
    public void addToCart(java.lang.Long bookId) throws Exception {

        //find book local object with specified id
        BookLocal bookLocal = bookManager.findByPrimaryKey(bookId);

        //setup iterator for cart collection
        Iterator iterator = cart.iterator();

        //search for order line in cart
        while (iterator.hasNext()) {
            //get currently pointed order line local item
            OrderLineDTO orderLineDTO = (OrderLineDTO) iterator.next();

            //if matching found, throw an error
            if (orderLineDTO.getBookId().equals(bookId)) {
                throw new Exception("Book already in cart!");
            }
        }

        //if book is not in cart yet, add book to cart
        //create new order line DTO object
        OrderLineDTO orderLineDTO = new OrderLineDTO();

        //set all fields with provided data
        orderLineDTO.setBookId(bookId);
        orderLineDTO.setUnitPrice(bookLocal.getPrice());
        orderLineDTO.setQuantity(new Long(1));

        //add order line to cart
        cart.add(orderLineDTO);

    }

    //update cart with current session's cart
    public void updateCart(java.util.ArrayList cart) throws Exception {
        this.cart = cart;
    }

    //remove a book from cart
    public void removeFromCart(java.lang.Long bookId) throws Exception {

        //if cart is empty, throw an error
        if (cart.isEmpty()) {
            throw new Exception("Cart is empty!");
        } else {
            //find book local object with specified id
            bookManager.findByPrimaryKey(bookId);

            //setup iterator for cart collection
            Iterator iterator = cart.iterator();

            //search for order line in cart
            while (iterator.hasNext()) {
                //get currently pointed order line local item
                OrderLineDTO orderLineDTO = (OrderLineDTO) iterator.next();

                //if matching found, remove order line from cart and return
                if (orderLineDTO.getBookId().equals(bookId)) {
                    iterator.remove();
                    return;
                }
            }

            //if no matching found, throw an error
            throw new Exception("Boot is currently not in cart!");
        }

    }

    //modify cart item
    public void modifyCart(java.lang.Long bookId, java.lang.Long quantity) throws Exception {
        //setup iterator for cart collection
        Iterator iterator = cart.iterator();

        //search for order line in cart
        while (iterator.hasNext()) {
            //get currently pointed order line local item
            OrderLineDTO orderLineDTO = (OrderLineDTO) iterator.next();

            //if matching found, edit quantity and return
            if (orderLineDTO.getBookId().equals(bookId)) {
                orderLineDTO.setQuantity(quantity);
                return;
            }
        }

        //if no matching found, throw an error
        throw new Exception("There is no such cart item!");
    }

    //view shopping cart
    public ArrayList getCart() {

        return cart;

    }

    //get total cart value
    public Double getCartValue() throws Exception {

        //declare variable to store total amount
        double total = 0;

        //loop through each item of the shopping cart to add amount
        //declare iterator
        Iterator iterator = cart.iterator();
        while (iterator.hasNext()) {

            //get currently pointed order line local item
            OrderLineDTO orderLineDTO = (OrderLineDTO) iterator.next();

            //get book local object with specified id in the order line
            BookLocal book = bookManager.findByPrimaryKey(orderLineDTO.getBookId());

            //add cost to total
            total += book.getPrice().doubleValue() * orderLineDTO.getQuantity().longValue();
        }

        //return total amount
        return new Double(total);
    }

    //remove all items in shopping cart
    public void clearCart() {

        cart.clear();

    }

    //checkout and add shipping address
    public void checkOut(java.lang.String address) throws Exception {

        //if user is not logged in, throw an error
        if (user == null) {
            throw new Exception("Only logged in users can checkout!");
        } else if (cart.isEmpty()) {
            //if cart is empty, throw an error
            throw new Exception("Shopping cart empty!");
        } else {
            //else, set address for order and calculate order's fields

            order = new OrderDTO();

            //set shipping address
            order.setAddress(address);

            //set order date
            order.setDate(new Date());

            //set order total amount
            order.setAmount(getCartValue());
        }

    }

    //confirm checkout
    public void confirmOrder(java.lang.Boolean confirmation) throws Exception {

        //if order is confirmed, add order and order lines from shopping cart to database
        if (confirmation.booleanValue()) {
            //add order to database and get returned order local object
            OrderLocal orderLocal = orderManager.create(order);

            //set user for order local object
            orderLocal.setUser(user);

            //setup iterator for cart collection
            Iterator iterator = cart.iterator();

            int counter = 1;

            //set order id and add each order line to database
            while (iterator.hasNext()) {
                //get currently pointed order line item
                OrderLineDTO tempOrderLine = (OrderLineDTO) iterator.next();

                //set order id for order line dto
                tempOrderLine.setOrderId(orderLocal.getId());

                //add new order line to database
                OrderLineLocal orderLineLocal = orderLineManager.create(tempOrderLine);

                //get book local object
                BookLocal bookLocal = bookManager.findByPrimaryKey(tempOrderLine.getBookId());

                //set order for order line local object
                orderLineLocal.setOrder(orderLocal);

                //set book for order line local object
                orderLineLocal.setBook(bookLocal);
            }

            //reset order and cart
            order = new OrderDTO();
            cart.clear();
        }

    }

    //view orders placed in the past
    public ArrayList getPastOrders() {

        //if user not logged in, return null
        if (user == null) {
            return null;
        }

        //get past orders collection for current user
        Collection pastOrders = user.getOrders();

        //declare temp variables
        Iterator iterator = pastOrders.iterator();   //iterator to loop through order collection
        OrderLocal orderLocal = null;   //local object
        OrderDTO orderDTO = null;   //data transfer object

        //declare output variable
        ArrayList orders = new ArrayList();

        //transform each order local object into order DTO and store in a new collection
        while (iterator.hasNext()) {
            //get currently pointed order local item
            orderLocal = (OrderLocal) iterator.next();

            //convert to DTO object
            orderDTO = Utils.convertToOrderDTO(orderLocal);

            //add DTO object to collection
            orders.add(orderDTO);
        }

        //return order collection
        return orders;

    }

    //view items of past orders
    public ArrayList getPastOrderItems() {

        //if user not logged in, return null
        if (user == null) {
            return null;
        }

        //get past order collection
        Collection pastOrders = user.getOrders();

        //declare temp variables
        Iterator orderIterator = pastOrders.iterator();   //iterator to loop through order collection
        Iterator orderLineIterator = null;  //iterator to loop through order line collection
        OrderLocal orderLocal = null;    //order local object
        OrderLineLocal orderLineLocal = null;   //order line local object
        OrderLineDTO orderLineDTO = null;   //order line data transfer object

        //declare output variable
        ArrayList orders = new ArrayList();

        //declare temp collection variable to store order lines for each order
        ArrayList orderLines = null;

        //transform each order line local object into order line DTO and store in a new collections
        //then, add new collections to output collection
        while (orderIterator.hasNext()) {
            //get currently pointed order local item
            orderLocal = (OrderLocal) orderIterator.next();

            //setup iterator to loop through order line items
            orderLineIterator = orderLocal.getOrderLines().iterator();

            //create new order line collection
            orderLines = new ArrayList();

            //transform each order line local to
            while (orderLineIterator.hasNext()) {
                //get currently pointed order line local item
                orderLineLocal = (OrderLineLocal) orderLineIterator.next();

                //convert to DTO object
                orderLineDTO = Utils.convertToOrderLineDTO(orderLineLocal);

                //add DTO object to collection
                orderLines.add(orderLineDTO);
            }

            //add new collection to output collection
            orders.add(orderLines);
        }

        //return order collection
        return orders;

    }

    //get all category, sorted by category sort order and name
    public ArrayList getAllCategories() throws Exception {

        //declare category collection variable
        ArrayList categories = null;

        //get all category local objects ordered by category sort order and name
        Collection allCategories = categoryManager.findAllOrderedByName();

        //setup iterator and output collection
        Iterator iterator = allCategories.iterator();
        categories = new ArrayList();

        //transform each category local object into category DTO and store in a new collection
        while (iterator.hasNext()) {

            //declare local object variable
            CategoryLocal categoryLocal = (CategoryLocal) iterator.next();

            //convert local object to DTO object and add to output collection
            categories.add(Utils.convertToCategoryDTO(categoryLocal));
        }

        return categories;

    }
}
