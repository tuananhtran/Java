package vn.edu.rmit.s3269999;

import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewProduct extends HttpServlet {

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
            out.println("<title>Servlet ViewProduct</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewProduct at " + request.getContextPath () + "</h1>");
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
//        processRequest(request, response);

        ProductLocal product = null;

        try {
            InitialContext ic = new InitialContext();
            Object lookup = ic.lookup("java:comp/env/ejb/Product");
            ProductLocalHome productHome = (ProductLocalHome) PortableRemoteObject.narrow(lookup, ProductLocalHome.class);
            product = productHome.findByPrimaryKey(new Long(request.getParameter("id")));
        } catch (Exception e) {
            response.sendError(response.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }

        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        writer.println("<html>");
        writer.println("<head><title>Online Shopping Store - View Product Details</title></head>");
        writer.println("<body>");
        writer.println("<h1>Product Details</h1>");
        writer.println("<ul>");
        writer.println("<li><b>ID:</b> " + product.getId().longValue() + "</li>");
        writer.println("<li><b>Name:</b> " + product.getName() + "</li>");
        writer.println("<li><b>Price:</b> $" + product.getPrice().doubleValue() + "</li>");
        writer.print("<li><a href='" + request.getContextPath() + "/cart?id=");
        writer.println(product.getId().longValue() + "'>Add to cart!</a></li></ul><br />");
        writer.println("<a href='" + request.getContextPath() + "'>Back to Home</a>");
        writer.println("</body></html>");
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
//        processRequest(request, response);

        doGet(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
