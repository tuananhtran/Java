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

public class Login extends HttpServlet {

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
            out.println("<title>Servlet Login</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath () + "</h1>");
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
        try {
            //processRequest(request, response);

            //get session control
            BookStoreManagerRemote manager = (BookStoreManagerRemote) request.getSession().getAttribute("manager");

            //if session manager is not initialized, set it up
            if (manager == null) {
                manager = Utils.getSessionControl();

                //update session variable
                request.getSession().setAttribute("manager", manager);
            }

            //get parameters
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String action = request.getParameter("action");

            //try to login
            manager.login(login, password);

            //if user successfully logged in, set session variable(s)
            request.getSession().setAttribute("user", manager.getCurrentUser());

            //get current shopping cart
            ArrayList cart = (ArrayList) request.getSession().getAttribute("cart");

            //if cart is initialized, update cart
            manager.updateCart(cart);

            //update total cart amount
            request.getSession().setAttribute("total", manager.getCartValue());

            //if action is "checkout", jump to checkout page
            if (action != null && action.equalsIgnoreCase("checkout")) {
                response.sendRedirect("checkout.jsp");
            } else {
                //else, return to homepage
                response.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            //if error occurs, display error details
            response.sendError(response.SC_INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
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
