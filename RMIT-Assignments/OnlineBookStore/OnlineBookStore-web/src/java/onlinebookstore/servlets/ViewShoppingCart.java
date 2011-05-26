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
import vn.com.onlinebookstore.dto.BookDTO;

public class ViewShoppingCart extends HttpServlet {

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
            out.println("<title>Servlet ViewShoppingCart</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewShoppingCart at " + request.getContextPath () + "</h1>");
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

            //get parameter(s)
            String idParam = request.getParameter("id");

            //if book id is provided, add book to shopping cart
            if (!(idParam == null || idParam.isEmpty())) {
                Long id = new Long(idParam);

                //get book with specified id
                BookDTO book = manager.getBook(id);

                //if book id is in database, add book to cart
                manager.addToCart(id);

                //get current books in cart
                ArrayList cartBooks = (ArrayList) request.getSession().getAttribute("cart_books");

                //add this book to cart
                cartBooks.add(book);

                //update cart session variables
                request.getSession().setAttribute("cart", manager.getCart());
                request.getSession().setAttribute("total", manager.getCartValue());
                request.getSession().setAttribute("cart_books", cartBooks);

                //notifiy successful transaction
                out.println("<script type='text/javascript'>");
                out.println("alert('Successfully added new book to shopping cart!');");
                out.println("</script>");
            }

            //forward request to list_category_books page
            request.getRequestDispatcher("shopping_cart.jsp").forward(request, response);
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
