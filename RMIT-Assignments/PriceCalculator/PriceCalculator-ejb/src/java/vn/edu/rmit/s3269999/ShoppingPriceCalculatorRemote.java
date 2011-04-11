/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.rmit.s3269999;

import java.rmi.RemoteException;
import java.util.List;
import javax.ejb.EJBObject;

public interface ShoppingPriceCalculatorRemote extends EJBObject {

    public double calculatePrice(List orders) throws RemoteException;
}
