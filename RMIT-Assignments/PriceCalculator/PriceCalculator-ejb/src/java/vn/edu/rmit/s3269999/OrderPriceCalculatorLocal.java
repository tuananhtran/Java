/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.rmit.s3269999;

import javax.ejb.EJBLocalObject;

public interface OrderPriceCalculatorLocal extends EJBLocalObject {

    public double calculatePrice(Order order);
}
