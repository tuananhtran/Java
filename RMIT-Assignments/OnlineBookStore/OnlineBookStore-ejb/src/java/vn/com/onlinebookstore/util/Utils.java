package vn.com.onlinebookstore.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import vn.com.onlinebookstore.*;
import vn.com.onlinebookstore.dto.*;

public class Utils {

    //get next id
    public static Long getNextId(String tableName) throws Exception {

        //create connection to datasource
        Connection connection = getDataSource().getConnection();

        //excecute query statement and fetch result
        PreparedStatement statement = connection.prepareStatement("SELECT MAX(id) FROM " + tableName);
        ResultSet result = statement.executeQuery();

        //if there is at least a book exist in database
        if (result.next()) {
            //get next id
            Long nextId = new Long(result.getLong(1) + 1);

            //close connection
            statement.close();
            connection.close();
            result.close();

            //return last id incremented by 1 as next id
            return nextId;
        }

        //close connection
        statement.close();
        connection.close();
        result.close();

        //if database is empty, return 1 as next id
        return new Long(1);
    }

    //get datasource
    private static DataSource getDataSource() throws Exception {
        InitialContext ic = new InitialContext();
        return (DataSource) ic.lookup("jdbc/cosc2465");
    }

    //convert category local object to category DTO object
    public static CategoryDTO convertToCategoryDTO(CategoryLocal categoryLocal) {

        //initialize category DTO object
        CategoryDTO categoryDTO = new CategoryDTO();

        //set all fields with provided data
        categoryDTO.setId(categoryLocal.getId());
        categoryDTO.setName(categoryLocal.getName());
        categoryDTO.setSortOrder(categoryLocal.getSortOrder());

        //return converted DTO object
        return categoryDTO;
    }

    //convert book local object to book DTO object
    public static BookDTO convertToBookDTO(BookLocal bookLocal) {

        //initialize book DTO object
        BookDTO bookDTO = new BookDTO();

        //set all fields with provided data
        bookDTO.setId(bookLocal.getId());
        bookDTO.setTitle(bookLocal.getTitle());
        bookDTO.setDescription(bookLocal.getDescription());
        bookDTO.setAuthor(bookLocal.getAuthor());
        bookDTO.setPublisher(bookLocal.getPublisher());
        bookDTO.setDatePublished(bookLocal.getDatePublished());
        bookDTO.setPrice(bookLocal.getPrice());
        bookDTO.setPhoto(bookLocal.getPhoto());
        bookDTO.setRatingValue(bookLocal.getRatingValue());
        bookDTO.setRatingCount(bookLocal.getRatingCount());
        bookDTO.setCategoryId(bookLocal.getCategory().getId());

        //return converted DTO object
        return bookDTO;
    }

    //convert comment local object to comment DTO object
    public static CommentDTO convertToCommentDTO(CommentLocal commentLocal) {

        //initialize comment DTO object
        CommentDTO commentDTO = new CommentDTO();

        //set all fields with provided data
        commentDTO.setId(commentLocal.getId());
        commentDTO.setRating(commentLocal.getRating());
        commentDTO.setContent(commentLocal.getContent());
        commentDTO.setUserId(commentLocal.getUser().getId());
        commentDTO.setBookId(commentLocal.getBook().getId());
        commentDTO.setDate(commentLocal.getDate());

        //return converted DTO object
        return commentDTO;
    }

    //convert order local object to order DTO object
    public static OrderDTO convertToOrderDTO(OrderLocal orderLocal) {

        //initialize order DTO object
        OrderDTO orderDTO = new OrderDTO();

        //set all fields with provided data
        orderDTO.setId(orderLocal.getId());
        orderDTO.setDate(orderLocal.getDate());
        orderDTO.setAmount(orderLocal.getAmount());
        orderDTO.setUserId(orderLocal.getUser().getId());
        orderDTO.setAddress(orderLocal.getAddress());

        //return converted DTO object
        return orderDTO;
    }

    //convert order line local object to order line DTO object
    public static OrderLineDTO convertToOrderLineDTO(OrderLineLocal orderLineLocal) {

        //initialize order line DTO object
        OrderLineDTO orderLineDTO = new OrderLineDTO();

        //set all fields with provided data
        orderLineDTO.setId(orderLineLocal.getId());
        orderLineDTO.setOrderId(orderLineLocal.getOrder().getId());
        orderLineDTO.setBookId(orderLineLocal.getBook().getId());
        orderLineDTO.setUnitPrice(orderLineLocal.getUnitPrice());
        orderLineDTO.setQuantity(orderLineLocal.getQuantity());

        //return converted DTO object
        return orderLineDTO;
    }
}
