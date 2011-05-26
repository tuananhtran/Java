package onlinebookstore.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import onlinebookstore.utils.Utils;
import vn.com.onlinebookstore.BookStoreManagerRemote;
import vn.com.onlinebookstore.dto.*;

public class GetBookDetails extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GetBookDetails</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetBookDetails at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */

            //get book id parameter
            String idParam = request.getParameter("id");

            //declare book id variable
            Long id = null;

            //get session control
            BookStoreManagerRemote manager = (BookStoreManagerRemote) request.getSession().getAttribute("manager");

            //if session manager is not initialized, set it up
            if (manager == null) {
                manager = Utils.getSessionControl();

                //update session variable
                request.getSession().setAttribute("manager", manager);
            }

            //if book id is provided, set it with provided value
            if (!(idParam == null || idParam.isEmpty())) {
                id = new Long(idParam);

                //and set session variable with book object of specified id
                BookDTO book = manager.getBook(id);
                request.setAttribute("book", book);

                //find and set session variable with category for this book
                CategoryDTO category = manager.getCategory(book.getCategoryId());
                request.setAttribute("category", category);

                //get all ccomments
                ArrayList comments = manager.getBookComments(id);
                //set collection to session variable
                request.setAttribute("comments", comments);

                //get authors for book comments
                ArrayList authors = manager.getUserNamesByBookComments(id);
                //set collection to session variable
                request.setAttribute("authors", authors);

                //forward request to list_categories page
                request.getRequestDispatcher("get_book_details.jsp").forward(request, response);
            }

            //if no id provided, throw an error
            throw new Exception("Invalid book id!");
        } catch (Exception e) {
            //if error occurs, display error details
            response.sendError(response.SC_INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
