package vn.edu.rmit.s3269999;

import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vn.edu.rmit.s3269999.dto.ProductDTO;

public class AddProduct extends HttpServlet {

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
            out.println("<title>Servlet AddProduct</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProduct at " + request.getContextPath () + "</h1>");
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

        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        writer.println("<html>");
        writer.println("<head><title>Online Shopping Store - Add Product To Cart</title></head>");
        writer.println("<body>");
        writer.println("<h1>Add Product To Database</h1>");
        writer.println("<form method='post' action='#'>");
        writer.println("<fieldset style='float:left'>");
        writer.println("<legend>Product Details</legend>");
        writer.println("<label for='name'>Name:</label>");
        writer.println("<input type='text' name='name' id='name' length='30' />");
        writer.println("<br /><label for='price'>Price:</label>");
        writer.println("<input type='text' name='price' id='price' length='5' />");
        writer.println("<br /><input type='submit' value='Add new product!' />");
        writer.println("</fieldset></form><br style='float:left;clear:left' /><br />");
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

        ProductDTO product = new ProductDTO();
        String productName = request.getParameter("name");
        String productPrice = request.getParameter("price");

        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        writer.println("<html>");
        writer.println("<head><title>Online Shopping Store - Add Product To Database</title></head>");
        writer.println("<body>");
        writer.println("<h1>Add Product To Database</h1>");
        writer.println("<form method='post' action='#'>");
        writer.println("<fieldset style='float:left'>");
        writer.println("<legend>Product Details</legend>");
        writer.println("<label for='name'>Name:</label>");
        writer.print("<input type='text' name='name' id='name' length='30' ");

        if (productName.isEmpty()) {
            writer.println("/><span style='color:red'> *Product name is required!</span>");
        } else if (productName.matches("^[-+]?\\d*(\\.\\d+)?$")) {
            writer.println("/><span style='color:red'> *Product name cannot be numeric!</span>");
        } else {
            writer.println("value='" + productName + "' />");
            product.setName(productName);
        }

        writer.println("<br /><label for='price'>Price:</label>");
        writer.print("<input type='text' name='price' id='price' length='5' ");

        if (productPrice.isEmpty()) {
            writer.println("/><span style='color:red'> *Product price is required!</span>");
        } else if (!productPrice.matches("^[-+]?\\d*(\\.\\d+)?$")) {
            writer.println("/><span style='color:red'> *Product price must be numeric!</span>");
        } else {
            double price = Double.parseDouble(productPrice);

            if (price <= 0) {
                writer.println("/><span style='color:red'> *Product price must be a positive number!</span>");
            } else {
                writer.println("value='" + productPrice + "' />");
                product.setPrice(new Double(price));
            }
        }

        writer.println("<br /><input type='submit' value='Add new product!' />");
        writer.println("</fieldset></form><br style='float:left;clear:left' /><br />");

        if (product.getName() != null && product.getPrice() != null) {
            try {
                InitialContext ic = new InitialContext();
                Object lookup = ic.lookup("java:comp/env/ejb/Product");
                ProductLocalHome productHome = (ProductLocalHome) PortableRemoteObject.narrow(lookup, ProductLocalHome.class);
                productHome.create(product);
            } catch (Exception e) {
                response.sendError(response.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            }

            writer.println("<p>Successfully added product with name " + productName + " and price " + productPrice + "!</p>");
        }

        writer.println("<a href='" + request.getContextPath() + "'>Back to Home</a><br />");
        writer.println("</body></html>");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
