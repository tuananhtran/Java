package vn.edu.rmit.s3269999;

import javax.ejb.EJBLocalObject;

public interface ProductLocal extends EJBLocalObject {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    Double getPrice();

    void setPrice(Double price);
}
