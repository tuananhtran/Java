package onlinebookstore.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import onlinebookstore.utils.Utils;
import vn.com.onlinebookstore.BookStoreManagerRemote;

public class UpdateCart extends HttpServlet {

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
            out.println("<title>Servlet UpdateCart</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateCart at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */

            //get parameter
            String update = request.getParameter("update");

            //if update string not provided, redirect to homepage
            if (update == null || update.isEmpty()) {
                response.sendRedirect("index.jsp");
            }

            //get session control
            BookStoreManagerRemote manager = (BookStoreManagerRemote) request.getSession().getAttribute("manager");

            //if session manager is not initialized, set it up
            if (manager == null) {
                manager = Utils.getSessionControl();

                //update session variable
                request.getSession().setAttribute("manager", manager);
            }

            //split update string into fields, ready for updating cart
            String fields[] = update.split(",");

            //loop through each field and modify cart
            for (int i = 0; i < fields.length; i += 3) {

                //if remove checkbox checked, remove item from cart
                if (Boolean.parseBoolean(fields[i + 2])) {
                    manager.removeFromCart(new Long(fields[i]));
                } else {
                    //else, mofidy quantity value
                    manager.modifyCart(new Long(fields[i]), new Long(fields[i + 1]));
                }

            }

            //update session variables
            request.getSession().setAttribute("cart", manager.getCart());
            request.getSession().setAttribute("total", manager.getCartValue());

            //if no error occurs, display success message and return to shopping cart
            out.println("<script type='text/javascript'>");
            out.println("alert('Shopping cart successfully updated!');");
            out.println("window.location = 'cart.jsp';");
            out.println("</script>");

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
