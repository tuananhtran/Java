/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.rmit.s3269999;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface ShoppingPriceCalculatorRemoteHome extends EJBHome {

    vn.edu.rmit.s3269999.ShoppingPriceCalculatorRemote create() throws CreateException, RemoteException;
}
