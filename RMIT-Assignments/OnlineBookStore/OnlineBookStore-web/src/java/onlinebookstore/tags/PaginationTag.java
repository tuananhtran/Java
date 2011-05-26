package onlinebookstore.tags;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PaginationTag extends SimpleTagSupport {

    //declare variables to store current page number and number of pages
    private Integer page;
    private Integer numberOfPages;
    private String URL;

    /**
     * Called by the container to invoke this tag. 
     * The implementation of this method is provided by the tag library developer,
     * and handles all tag processing, body iteration, etc.
     */
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();

        try {
            // TODO: insert code to write html before writing the body content.
            // e.g.:
            //
            // out.println("<strong>" + attribute_1 + "</strong>");
            // out.println("    <blockquote>");

            out.println("<div class='pagination'>");

            //if current page number is 1, disable previous button
            if (page != null && page.intValue() == 1) {
                out.print("<span class='disabled'><<</span>");
            } else {
                //else, apply a hyperlink to it
                out.print("<a href='" + URL + "&page=" + (page.intValue() - 1) + "'><<</a>");
            }

            //if number of pages is less than 8, display only those buttons
            if (numberOfPages.intValue() < 8) {
                for (int i = 1; i <= numberOfPages.intValue(); i++) {
                    //if this is current page, format it
                    if (i == page.intValue()) {
                        out.print("<span class='current'>" + i + "</span>");
                    } else {
                        //else, apply hyperlink to it
                        out.print("<a href='" + URL + "&page=" + i + "'>" + i + "</a>");
                    }
                }
            } else {
                //declare number of button to be displayed before current page button
                int precede = 0;

                //if page number divided by 4, precede = 3
                if (page.intValue() % 4 == 0) {
                    precede = 3;
                } else if (page.intValue() % 3 == 0) {
                    //else, if page number divided by 3, precede = 2
                    precede = 2;
                } else if (page.intValue() % 2 == 0) {
                    //else, if page number divided by 2 (not by 4), precede = 1
                    precede = 1;
                }

                //if current page is one of the last 2 page, display for the first 4 page numbers
                if (page.intValue() == numberOfPages.intValue() - 1 || page.intValue() == numberOfPages.intValue()) {
                    for (int i = 1; i < 5; i++) {
                        out.print("<a href='" + URL + "?page=" + i + "'>" + i + "</a>");
                    }
                } else {
                    //else, display first 4 page buttons
                    for (int i = page.intValue() - precede; i < page.intValue() + 3 - precede; i++) {
                        //if this is current page, format it
                        if (i == page.intValue()) {
                            out.print("<span class='current'>" + i + "</span>");
                        } else {
                            //else, apply hyperlink to it
                            out.print("<a href='" + URL + "?page=" + i + "'>" + i + "</a>");
                        }
                    }
                }

                //display ellipsis(...) if needed
                if (page.intValue() + 4 - precede == numberOfPages.intValue() - 1) {
                    out.print("...");
                }

                //display last 2 buttons
                for (int i = numberOfPages.intValue() - 1; i < numberOfPages.intValue(); i++) {
                    //if this is current page, format it
                    if (i == page.intValue()) {
                        out.print("<span class='current'>" + i + "</span>");
                    } else {
                        //else, apply hyperlink to it
                        out.print("<a href='" + URL + "&page=" + i + "'>" + i + "</a>");
                    }
                }
            }

            //if current page number is the last, disable next button
            if (page != null && page.intValue() == numberOfPages.intValue()) {
                out.print("<span class='disabled'>>></span>");
            } else {
                //else, apply a hyperlink to it
                out.print("<a href='" + URL + "&page=" + (page.intValue() + 1) + "'>>></a>");
            }


            out.println("</div>");

            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }

            // TODO: insert code to write html after writing the body content.
            // e.g.:
            //
            // out.println("    </blockquote>");

        } catch (java.io.IOException ex) {
            throw new JspException("Error in PaginationTag tag", ex);
        }
    }

    //set page number
    public void setPage(Integer page) throws Exception {
        //if page number is not provided, set it to 1
        if (page == null) {
            page = new Integer(1);
        }

        //if page number less than 1 or larger than number of pages, throw an error
        if (page.intValue() < 1 || (numberOfPages != null && page.intValue() > numberOfPages.intValue())) {
            throw new Exception("Invalid page number!");
        }

        this.page = page;
    }

    //set number of pages
    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    //set url for page buttons
    public void setURL(String URL) {
        this.URL = URL;
    }
}
