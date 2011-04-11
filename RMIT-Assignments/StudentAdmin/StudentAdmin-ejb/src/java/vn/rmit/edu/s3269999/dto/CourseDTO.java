package vn.rmit.edu.s3269999.dto;

public class CourseDTO implements java.io.Serializable {

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
        if (obj instanceof CourseDTO) {
            if (((CourseDTO) obj).getName().equals(name)) {
                return true;
            }
        }

        return false;
    }

    public int hashCode() {
        return id.hashCode() ^ name.hashCode();
    }
}
