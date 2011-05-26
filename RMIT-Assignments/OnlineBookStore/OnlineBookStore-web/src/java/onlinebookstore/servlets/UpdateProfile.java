package onlinebookstore.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import onlinebookstore.utils.Utils;
import vn.com.onlinebookstore.BookStoreManagerRemote;
import vn.com.onlinebookstore.dto.UserDTO;

public class UpdateProfile extends HttpServlet {

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
            out.println("<title>Servlet UpdateProfile</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProfile at " + request.getContextPath () + "</h1>");
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
            //get current user (if existed)
            UserDTO user = (UserDTO) request.getSession().getAttribute("user");

            //if user not logged in, return to homepage
            if (user == null) {
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

            //get parameters
            String passwordParam = request.getParameter("password");
            String emailParam = request.getParameter("email");
            String nameParam = request.getParameter("name");
            String phoneParam = request.getParameter("phone");
            String birthdayParam = request.getParameter("birthday");

            //create new data transfer object, ready for exchanging data
            UserDTO updatedUser = new UserDTO();

            //set DTO object with provided data
            updatedUser.setLogin(user.getLogin());
            updatedUser.setPassword(passwordParam);
            updatedUser.setEmail(emailParam);
            updatedUser.setName(nameParam);
            if (!(phoneParam == null || phoneParam.isEmpty())) {
                updatedUser.setPhone(new Integer(phoneParam));
            }
            if (!(birthdayParam == null || birthdayParam.isEmpty())) {
                updatedUser.setBirthday(new Date(birthdayParam));
            }

            //update user information
            manager.updateProfile(updatedUser);

            //update user session variable
            request.getSession().setAttribute("user", manager.getCurrentUser());

            //if no error occurs, display success message and return to my account page
            out.println("<script type='text/javascript'>");
            out.println("alert('Personal information successfully updated!');");
            out.println("window.location = 'myaccount.jsp';");
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
