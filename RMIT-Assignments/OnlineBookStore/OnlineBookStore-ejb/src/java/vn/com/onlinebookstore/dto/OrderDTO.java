package vn.com.onlinebookstore.dto;

import java.util.Date;

public class OrderDTO implements java.io.Serializable {

    //declare fields for Order entity
    private Long id;    //id
    private Date date;  //order date
    private Double amount;  //order total amount
    private Long userId;    //user id (foreign key)
    private String address; //shipping address

    //accessor for id
    public Long getId() {
        return id;
    }

    //mutator for id
    public void setId(Long id) {
        this.id = id;
    }

    //accessor for order date
    public Date getDate() {
        return date;
    }

    //mutator for order date
    public void setDate(Date date) {
        this.date = date;
    }

    //accessor for order total amount
    public Double getAmount() {
        return amount;
    }

    //mutator for order total amount
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    //accessor for user id
    public Long getUserId() {
        return userId;
    }

    //mutator for user id
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    //accessor for shipping address
    public String getAddress() {
        return address;
    }

    //mutator for shipping address
    public void setAddress(String address) {
        this.address = address;
    }

    //check for similarity
    public boolean equals(Object obj) {
        if (obj instanceof OrderDTO) {
            OrderDTO other = (OrderDTO) obj;
            if (other.getId().equals(id) && other.getDate().equals(date)
                    && other.getAddress().equals(amount)
                    && other.getUserId().equals(userId)
                    && other.getAddress().equals(address)) {
                return true;
            }
        }

        return false;
    }

    //get entity's hash code
    public int hashCode() {
        return id.hashCode() ^ date.hashCode() ^ amount.hashCode()
                ^ userId.hashCode() ^ address.hashCode();
    }
}
