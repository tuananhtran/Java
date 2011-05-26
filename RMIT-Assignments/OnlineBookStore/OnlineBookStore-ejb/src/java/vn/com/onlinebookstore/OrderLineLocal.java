package vn.com.onlinebookstore;

import javax.ejb.EJBLocalObject;

public interface OrderLineLocal extends EJBLocalObject {

    Long getId();

    void setId(Long id);

    Double getUnitPrice();

    void setUnitPrice(Double unitPrice);

    Long getQuantity();

    void setQuantity(Long quantity);

    OrderLocal getOrder();

    void setOrder(OrderLocal order);

    BookLocal getBook();

    void setBook(BookLocal book);
}
