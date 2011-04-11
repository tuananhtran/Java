package vn.edu.rmit.s3269999;

import javax.ejb.EJBLocalObject;

public interface ProductCMPLocal extends EJBLocalObject {

    Integer getProductID();

    void setProductID(Integer productID);

    String getProductName();

    void setProductName(String productName);

    Double getProductPrice();

    void setProductPrice(Double productPrice);
}
