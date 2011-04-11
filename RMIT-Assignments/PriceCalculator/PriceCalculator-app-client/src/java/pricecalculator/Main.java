/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pricecalculator;

import java.util.ArrayList;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import vn.edu.rmit.s3269999.*;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ShoppingPriceCalculatorRemote shoppingPriceCalculator;
        try {
            InitialContext ic = new InitialContext();
            String jndi = "ejb/ShoppingPriceCalculator";
            Object lookup = ic.lookup(jndi);

            ShoppingPriceCalculatorRemoteHome remoteBean =
                    (ShoppingPriceCalculatorRemoteHome) PortableRemoteObject.narrow(
                    lookup, ShoppingPriceCalculatorRemoteHome.class);

            shoppingPriceCalculator = remoteBean.create();

            System.out.println("Remote and Local EJBs created!\n");

            System.out.println("Creating list of orders...");
            ArrayList orders = new ArrayList();
            for (int i = 0; i < 10; i++) {
                Order order =
                        new Order("Product " + i,
                        (int) (Math.random() * 100000) / 100.0, (long) (Math.random() * 100));
                orders.add(order);
                System.out.println("Product " + i + ":");
                System.out.println(" |_ Product Name: " + order.getProductName());
                System.out.println(" |_ Unit Price: " + order.getUnitPrice());
                System.out.println(" |_ Quantity: " + order.getQuantity());
            }
            System.out.println("\n=> Total price of all orders = "
                    + (int) (shoppingPriceCalculator.calculatePrice(orders) * 100) / 100.0);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
