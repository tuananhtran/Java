/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.rmit.s3269999;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

public interface OrderPriceCalculatorLocalHome extends EJBLocalHome {

    vn.edu.rmit.s3269999.OrderPriceCalculatorLocal create() throws CreateException;
}
