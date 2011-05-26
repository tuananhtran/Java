package onlinebookstore.tags;

import java.util.ArrayList;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import vn.com.onlinebookstore.dto.BookDTO;

public class BookListTag extends BodyTagSupport {

    //declare variables to store book collection and page number
    private ArrayList books = null;
    private Integer page = null;
    //declare counter variable to loop through list
    private int counter = 0;

    /** 
     * Creates new instance of tag handler 
     */
    public BookListTag() {
        super();
    }

    public int doStartTag() throws JspException {

        //get current pointed book item of specified page
        BookDTO book = (BookDTO) ((ArrayList) books.get(page.intValue() - 1)).get(counter++);

        //set session variables with retrieved data
        pageContext.setAttribute("book", book);

        return EVAL_BODY_INCLUDE;
    }

    public int doEndTag() throws JspException {
        counter = 0;
        return EVAL_PAGE;
    }

    public int doAfterBody() throws JspException {
        //if end of page not reached, continue to fetch data
        if (counter < ((ArrayList) books.get(page.intValue() - 1)).size()) {
            //get current pointed book item of specified page
            BookDTO book = (BookDTO) ((ArrayList) books.get(page.intValue() - 1)).get(counter++);

            //set session variables with retrieved data
            pageContext.setAttribute("book", book);

            //re-evaluate body
            return EVAL_BODY_AGAIN;
        }

        return SKIP_BODY;
    }

    //set book collection
    public void setBooks(ArrayList books) {
        this.books = books;
    }

    //set page number
    public void setPage(Integer page) throws Exception {
        //if page number is not provided, set it to 1
        if (page == null) {
            page = new Integer(1);
        }

        //if page number less than 1 or larger than number of pages, throw an error
        if (page.intValue() < 1 || page.intValue() > books.size()) {
            throw new Exception("Invalid page number!");
        }

        this.page = page;
    }
}
