package onlinestorefront;

import java.util.Arrays;
import java.util.Collection;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import vn.edu.rmit.s3269999.dto.ProductDTO;

public class ListProductsTag extends SimpleTagSupport {

    private Collection products;
    private String name;
    private String order;

    /**
     * Called by the container to invoke this tag. 
     * The implementation of this method is provided by the tag library developer,
     * and handles all tag processing, body iteration, etc.
     */
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();

        try {
            // TODO: insert code to write html before writing the body content.
            // e.g.:
            //
            // out.println("<strong>" + attribute_1 + "</strong>");
            // out.println("    <blockquote>");

            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }

            // TODO: insert code to write html after writing the body content.
            // e.g.:
            //
            // out.println("    </blockquote>");

            if (name == null || name.isEmpty()) {
                name = "name";
            }

            if (order == null || order.isEmpty()) {
                order = "asc";
            } else if (order.equalsIgnoreCase("ascending")) {
                order = "asc";
            } else {
                order = "desc";
            }

            ProductDTO productDTOs[] = (ProductDTO[]) products.toArray(new ProductDTO[products.size()]);
            ProductDTO sorted[] = new ProductDTO[productDTOs.length];

            if (name.equalsIgnoreCase("name")) {
                java.util.List namelist = new java.util.ArrayList();
                for (int i = 0; i < productDTOs.length; i++) {
                    namelist.add(productDTOs[i].getName());
                }
                java.util.Collections.sort(namelist);

                String names[] = (String[]) namelist.toArray(new String[productDTOs.length]);

                for (int i = 0; i < productDTOs.length; i++) {
                    for (int j = 0; j < productDTOs.length; j++) {
                        if (names[i].equals(productDTOs[j].getName())) {
                            sorted[i] = productDTOs[j];
                        }
                    }
                }
            } else {
                double prices[] = new double[products.size()];

                for (int i = 0; i < productDTOs.length; i++) {
                    prices[0] = productDTOs[0].getPrice().doubleValue();
                }

                Arrays.sort(prices);

                for (int i = 0; i < productDTOs.length; i++) {
                    for (int j = 0; j < productDTOs.length; j++) {
                        if (prices[i] == productDTOs[j].getPrice().doubleValue()) {
                            sorted[i] = productDTOs[j];
                        }
                    }
                }
            }

            out.println("<table border='5' cellpadding='5'>");
            out.println("<tr>");
            out.println("<th bgcolor='lightgray' align='center'>ID</th>");
            out.println("<th bgcolor='lightgray' align='center'>Name</th>");
            out.println("<th bgcolor='lightgray' align='center'>Price</th>");
            out.println("</tr>");

            for (int i = 0; i < productDTOs.length; i++) {
                out.println("<tr>");
                if (order.equalsIgnoreCase("asc")) {
                    out.println("<td align='right'>" + productDTOs[i].getId().longValue() + "</td>");
                    out.println("<td>" + productDTOs[i].getName() + "</td>");
                    out.println("<td>$" + productDTOs[i].getPrice().doubleValue() + "</td>");
                } else {
                    out.println("<td align='right'>" + productDTOs[productDTOs.length - 1 - i].getId().longValue() + "</td>");
                    out.println("<td>" + productDTOs[productDTOs.length - 1 - i].getName() + "</td>");
                    out.println("<td>$" + productDTOs[productDTOs.length - 1 - i].getPrice().doubleValue() + "</td>");
                }
                out.println("</tr>");
            }

            out.println("</table>");
        } catch (java.io.IOException ex) {
            throw new JspException("Error in ListProductsTag tag", ex);
        }
    }

    public void setProducts(Collection products) {
        this.products = products;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
