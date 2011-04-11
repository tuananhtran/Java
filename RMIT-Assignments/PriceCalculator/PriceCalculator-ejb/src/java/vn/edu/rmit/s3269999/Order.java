/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.rmit.s3269999;

import java.io.Serializable;

public class Order implements Serializable {

    private String productName;
    private double unitPrice;
    private long quantity;

    public Order(String productName, double unitPrice, long quantity) {
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public long getQuantity() {
        return quantity;
    }
}
