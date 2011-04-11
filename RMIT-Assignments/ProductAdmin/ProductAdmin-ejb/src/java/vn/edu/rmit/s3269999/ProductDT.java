package vn.edu.rmit.s3269999;

public class ProductDT implements java.io.Serializable {

    private Integer productID;
    private String productName;
    private Double productPrice;

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ProductDT) {
            ProductDT product = (ProductDT) obj;
            return (product.getProductID().equals(productID)
                    && product.getProductName().equals(productName)
                    && product.getProductPrice().equals(productPrice));
        }
        return false;
    }

    public int hashCode() {
        return (productID.hashCode() ^ productName.hashCode() ^ productPrice.hashCode());
    }
}
