package vn.rmit.edu.s3269999.dto;

public class StudentDTO implements java.io.Serializable {

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object obj) {
        return (obj instanceof StudentDTO && ((StudentDTO) obj).getName().equals(name));
    }

    public int hashCode() {
        return id.hashCode() ^ name.hashCode();
    }
}
