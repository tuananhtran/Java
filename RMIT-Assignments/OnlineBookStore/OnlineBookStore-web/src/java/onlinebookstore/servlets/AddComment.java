package onlinebookstore.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import onlinebookstore.utils.Utils;
import vn.com.onlinebookstore.BookStoreManagerRemote;
import vn.com.onlinebookstore.dto.CommentDTO;
import vn.com.onlinebookstore.dto.UserDTO;

public class AddComment extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.jsp");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddComment</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddComment at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
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
        //processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //get session control
            BookStoreManagerRemote manager = (BookStoreManagerRemote) request.getSession().getAttribute("manager");

            //if session manager is not initialized, set it up
            if (manager == null) {
                manager = Utils.getSessionControl();

                //update session variable
                request.getSession().setAttribute("manager", manager);
            }

            //get parameters
            String ratingParam = request.getParameter("rating");
            String contentParam = request.getParameter("content");
            String bookIdParam = request.getParameter("bookId");


            //declare variables to be set as session variables
            Integer rating = new Integer(ratingParam);
            String content = contentParam;
            Long userId = ((UserDTO) request.getSession().getAttribute("user")).getId();
            Long bookId = new Long(bookIdParam);

            //decalre data transfer object, ready for transaction
            CommentDTO comment = new CommentDTO();

            //set DTO object with retrieved data
            comment.setUserId(userId);
            comment.setBookId(bookId);
            comment.setRating(rating);
            comment.setContent(content);

            //attempt to add comment
            manager.addComment(comment);

            //if no error occurs, display confirmation message and return to book details page
            out.println("<script type='text/javascript'>");
            out.println("alert('Successfully added new comment!');");
            out.println("window.location = 'details.jsp?id=" + bookIdParam + "';");
            out.println("</script>");
        } catch (Exception e) {
            //if error occurs, display error details
            response.sendError(response.SC_INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        } finally {
            out.close();
        }

    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
