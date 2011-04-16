package vn.edu.rmit.s3269999;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShoppingCart extends HttpServlet {

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
            out.println("<title>Servlet ShoppingCart</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShoppingCart at " + request.getContextPath () + "</h1>");
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

        HttpSession session = request.getSession();
        if (session.getAttribute("cart") == null) {
            session.setAttribute("cart", new ArrayList());
        }
        Collection cart = (Collection) session.getAttribute("cart");

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        writer.println("<html>");
        writer.println("<head><title>Online Shopping Store - View Shopping Cart</title></head>");
        writer.println("<body>");
        writer.println("<h1>Shopping Cart</h1>");
        writer.println("<table border='5' cellpadding='5'>");
        writer.println("<tr>");
        writer.println("<th bgcolor='lightgray' align='center'>No.</th>");
        writer.println("<th bgcolor='lightgray' align='center'>Product Name</th>");
        writer.println("<th bgcolor='lightgray' align='center'>Price</th>");
        writer.println("</tr>");

        int counter = 1;
        double sum = 0;
        ProductLocal newProduct = null;
        try {
            InitialContext ic = new InitialContext();
            Object lookup = ic.lookup("java:comp/env/ejb/Product");
            ProductLocalHome productHome = (ProductLocalHome) PortableRemoteObject.narrow(lookup, ProductLocalHome.class);

            if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
                Long newProductId = new Long(request.getParameter("id"));
                newProduct = productHome.findByPrimaryKey(newProductId);
                if (newProduct != null) {
                    cart.add(newProductId);
                }
            }

            Iterator iterator = cart.iterator();
            while (iterator.hasNext()) {
                ProductLocal product = productHome.findByPrimaryKey((Long) iterator.next());

                writer.println("<tr>");
                writer.println("<td align='right'>" + (counter++) + "</td>");
                writer.println("<td align='left'>" + product.getName() + "</td>");
                writer.println("<td align='right'>$" + product.getPrice().doubleValue() + "</td>");
                writer.println("</tr>");

                sum += product.getPrice().doubleValue();
            }
        } catch (Exception e) {
            response.sendError(response.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }

        writer.println("<tr>");
        writer.println("<th bgcolor='lightgray' align='center'>#</th>");
        writer.println("<th bgcolor='lightgray' align='right'>Total</th>");
        writer.println("<th bgcolor='yellow' align='right'>$" + sum + "</th>");
        writer.println("</tr></table><br />");

        if (newProduct != null) {
            writer.print("Successfully added product with name " + newProduct.getName());
            writer.println(" and price $" + newProduct.getPrice().doubleValue() + " to cart!");
        }

        writer.println("<br /><a href='" + request.getContextPath() + "'>Back to Home</a>");
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
