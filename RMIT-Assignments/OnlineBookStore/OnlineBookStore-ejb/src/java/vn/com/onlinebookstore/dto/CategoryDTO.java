package vn.com.onlinebookstore.dto;

public class CategoryDTO implements java.io.Serializable {

    //declare fields for Category entity
    private Long id;    //category id
    private String name;    //category name
    private Integer sortOrder;  //sort order

    //accessor for category id
    public Long getId() {
        return id;
    }

    //mutator for category id
    public void setId(Long id) {
        this.id = id;
    }

    //accessor for category name
    public String getName() {
        return name;
    }

    //mutator for category name
    public void setName(String name) {
        this.name = name;
    }

    //accessor for sort order
    public Integer getSortOrder() {
        return sortOrder;
    }

    //mutator for sort order
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    //check for similarity
    public boolean equals(Object obj) {
        if (obj instanceof CategoryDTO) {
            CategoryDTO other = (CategoryDTO) obj;
            if (other.getId().equals(id) && other.getName().equals(name)
                    && other.getSortOrder().equals(sortOrder)) {
                return true;
            }
        }

        return false;
    }

    //get entity's hash code
    public int hashCode() {
        return id.hashCode() ^ name.hashCode() ^ sortOrder.hashCode();
    }
}
