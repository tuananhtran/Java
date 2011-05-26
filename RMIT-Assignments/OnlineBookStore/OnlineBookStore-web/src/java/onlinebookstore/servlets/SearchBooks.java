package onlinebookstore.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import onlinebookstore.utils.Utils;
import vn.com.onlinebookstore.BookStoreManagerRemote;

public class SearchBooks extends HttpServlet {

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

            //get parameters
            String titleParam = request.getParameter("title");
            String authorParam = request.getParameter("author");
            String categoryParam = request.getParameter("category");
            String pageParam = request.getParameter("page");

            //declare variables to pass to session variable
            String title = null;
            String author = null;
            Long category = null;
            Integer page = null;

            //declare temp variable
            String[] words = null;

            //if title not provided, set it to blank
            if (titleParam == null || titleParam.isEmpty()) {
                title = "";
            } else {
                //else, set it with provided value

                //format title input
                words = titleParam.split("\\s");
                titleParam = "";
                for (int i = 0; i < words.length; i++) {
                    titleParam += words[i].substring(0, 1).toUpperCase()
                            + words[i].substring(1).toLowerCase();

                    if (i < words.length - 1) {
                        titleParam += " ";
                    }
                }

                title = titleParam;
            }

            //if author not provided, set it to blank
            if (authorParam == null || authorParam.isEmpty()) {
                author = "";
            } else {
                //else, set it with provided value

                //format author input
                words = authorParam.split("\\s");
                authorParam = "";
                for (int i = 0; i < words.length; i++) {
                    authorParam += words[i].substring(0, 1).toUpperCase()
                            + words[i].substring(1).toLowerCase();

                    if (i < words.length - 1) {
                        authorParam += " ";
                    }
                }

                author = authorParam;
            }

            //if category id is provided and not zero, set category variable with its value
            if (!(categoryParam == null || categoryParam.isEmpty() || Integer.parseInt(categoryParam) == 0)) {
                category = new Long(categoryParam);
            }

            //if page number not provided, set it to 1
            if (pageParam == null || pageParam.isEmpty()) {
                page = new Integer(1);
            } else {
                //else, set it with provided value
                page = new Integer(pageParam);
            }

            //set session variable with page number
            request.setAttribute("page", page);

            //declare book collection to be displayed for specified search condition
            java.util.ArrayList books = manager.searchBooks(title, author, category, new Integer(getServletContext().getInitParameter("pageSize")));

            //store collection to session variable
            request.setAttribute("books", books);

            //store number of pages in session variable
            request.setAttribute("numberOfPages", new Integer(books.size()));

            //build category site URL
            String URL = "search.jsp?title=" + (titleParam == null ? "" : titleParam)
                    + "&author=" + (authorParam == null ? "" : authorParam)
                    + "&category=" + (categoryParam == null ? "" : categoryParam);

            //set URL to session variable
            request.setAttribute("URL", URL);

            //forward request to list_category_books page
            request.getRequestDispatcher("search_books.jsp").forward(request, response);
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
