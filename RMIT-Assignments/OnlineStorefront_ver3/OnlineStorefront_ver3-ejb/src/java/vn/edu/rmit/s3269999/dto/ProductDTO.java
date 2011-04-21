package vn.edu.rmit.s3269999.dto;

public class ProductDTO implements java.io.Serializable {

    private Long id;
    private String name;
    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ProductDTO) {
            ProductDTO product = (ProductDTO) obj;

            if (product.getId().equals(id) && product.getName().equals(name) && product.getPrice().equals(price)) {
                return true;
            }
        }

        return false;
    }

    public int hashCode() {
        return id.hashCode() ^ name.hashCode() ^ price.hashCode();
    }
}
