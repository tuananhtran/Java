package vn.com.onlinebookstore.dto;

public class OrderLineDTO implements java.io.Serializable {

    //declare fields for Order_Line entity
    private Long id;    //id
    private Long orderId;   //order id (foreign key)
    private Long bookId;    //book id (foreign key)
    private Double unitPrice;   //unit price
    private Long quantity;  //quantity

    //accessor for id
    public Long getId() {
        return id;
    }

    //mutator for id
    public void setId(Long id) {
        this.id = id;
    }

    //accessor for order id
    public Long getOrderId() {
        return orderId;
    }

    //mutator for order id
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    //accessor for book id
    public Long getBookId() {
        return bookId;
    }

    //mutator for book id
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    //accessor for unit price
    public Double getUnitPrice() {
        return unitPrice;
    }

    //mutator for unit price
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    //accessor for quantity
    public Long getQuantity() {
        return quantity;
    }

    //mutator for quantity
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    //check for similarity
    public boolean equals(Object obj) {
        if (obj instanceof OrderLineDTO) {
            OrderLineDTO other = (OrderLineDTO) obj;
            if (other.getId().equals(id) && other.getOrderId().equals(orderId)
                    && other.getBookId().equals(bookId)
                    && other.getUnitPrice().equals(unitPrice)
                    && other.getQuantity().equals(quantity)) {
                return true;
            }
        }

        return false;
    }

    //get entity's hash code
    public int hashCode() {
        return id.hashCode() ^ orderId.hashCode() ^ bookId.hashCode()
                ^ unitPrice.hashCode() ^ quantity.hashCode();
    }
}
