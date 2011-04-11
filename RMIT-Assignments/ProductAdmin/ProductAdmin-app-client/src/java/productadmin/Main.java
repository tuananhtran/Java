package productadmin;

import java.util.Scanner;
import javax.ejb.Handle;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import vn.edu.rmit.s3269999.ProductAdminRemote;
import vn.edu.rmit.s3269999.ProductAdminRemoteHome;
import vn.edu.rmit.s3269999.ProductDT;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            InitialContext ic = new InitialContext();
            Object productAdminLookup = ic.lookup("ejb/ProductAdmin");

            ProductAdminRemoteHome productAdminHome =
                    (ProductAdminRemoteHome) PortableRemoteObject.narrow(
                    productAdminLookup, ProductAdminRemoteHome.class);

            ProductAdminRemote productAdmin = productAdminHome.create();

            System.out.println("Adding 5 products...");
            ProductDT product = null;
            int currentID;
            for (int i = 1; i < 6; i++) {
                product = new ProductDT();
                System.out.println("Product #" + i + ":");
                System.out.println("  |_ Name: Test product " + i);
                product.setProductName("Test product " + i);
                System.out.println("  |_ Price: " + i + "0,000");
                product.setProductPrice(new Double(i * 10000));
                System.out.println("Attempting to add new product...");
                currentID = productAdmin.addProduct(product);
                System.out.println("Successfully added new product with id: " + currentID);
                System.out.println();
            }

            System.out.println();
            System.out.println("Finding product with id 3...");
            product = productAdmin.getProductByID(3);
            System.out.println("Product #3:");
            System.out.println("  |_ Id: " + product.getProductID());
            System.out.println("  |_ Name: " + product.getProductName());
            System.out.println("  |_ Price: " + product.getProductPrice());

            Scanner sc = new Scanner(System.in);

            System.out.println();
            System.out.println();
            System.out.println("Please check your database before deleting Product #5!");
            sc.nextLine();
            System.out.println("Deleting product with id 5...");
            productAdmin.deleteProduct(5);
            System.out.println("Product #5 reference: " + productAdmin.getProductByID(5));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
