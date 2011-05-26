package onlinebookstore.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import onlinebookstore.utils.Utils;
import vn.com.onlinebookstore.BookStoreManagerRemote;
import vn.com.onlinebookstore.dto.CategoryDTO;

public class ListCategoryBooks extends HttpServlet {

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
            out.println("<title>Servlet ListCategoryBooks</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListCategoryBooks at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */

            //get session control
            BookStoreManagerRemote manager = (BookStoreManagerRemote) request.getSession().getAttribute("manager");

            //if session manager is not initialized, set it up
            if (manager == null) {
                manager = Utils.getSessionControl();

                //update session variable
                request.getSession().setAttribute("manager", manager);
            }

            //get category id and page number parameters
            String idParam = request.getParameter("id");
            String pageParam = request.getParameter("page");

            //declare category id and page number variables
            Long id = null;
            Integer page = null;

            //if page number not provided, set it to 1
            if (pageParam == null || pageParam.isEmpty()) {
                page = new Integer(1);
            } else {
                //else, set it with provided value
                page = new Integer(pageParam);
            }

            //set session variable with page number
            request.setAttribute("page", page);

            //if category id is provided, set it with provided value
            if (!(idParam == null || idParam.isEmpty())) {
                id = new Long(idParam);

                //and set session variable with category object of specified id
                CategoryDTO category = manager.getCategory(id);
                request.setAttribute("category", category);
            }

            //declare book collection to be displayed for specified category id and page number
            java.util.ArrayList books = manager.browseBooks(id, new Integer(getServletContext().getInitParameter("pageSize")));

            //store collection to session variable
            request.setAttribute("books", books);

            //store number of pages in session variable
            request.setAttribute("numberOfPages", new Integer(books.size()));

            //build category site URL
            String URL = "category.jsp?id=" + (idParam == null ? "" : idParam);

            //set URL to session variable
            request.setAttribute("URL", URL);

            //forward request to list_category_books page
            request.getRequestDispatcher("list_category_books.jsp").forward(request, response);
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
