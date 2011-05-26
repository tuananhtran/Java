package vn.com.onlinebookstore.dto;

import java.util.Date;

public class UserDTO implements java.io.Serializable {

    //declare fields for User entity
    private Long id;    //user id
    private String login;   //login name
    private String email;   //email address
    private String password;    //password
    private Integer phone;  //phone number
    private String name;    //full name
    private Date birthday;  //user's birthday
    private Date dateCreated;   //account creation date

    //accessor for id
    public Long getId() {
        return id;
    }

    //mutator for id
    public void setId(Long id) {
        this.id = id;
    }

    //accessor for login name
    public String getLogin() {
        return login;
    }

    //mutator for login name
    public void setLogin(String login) {
        this.login = login;
    }

    //accessor for email address
    public String getEmail() {
        return email;
    }

    //mutator for email address
    public void setEmail(String email) {
        this.email = email;
    }

    //accessor for password
    public String getPassword() {
        return password;
    }

    //mutator for password
    public void setPassword(String password) {
        this.password = password;
    }

    //accessor for phone number
    public Integer getPhone() {
        return phone;
    }

    //mutator for phone number
    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    //accessor for full name
    public String getName() {
        return name;
    }

    //mutator for full name
    public void setName(String name) {
        this.name = name;
    }

    //accessor for birthday
    public Date getBirthday() {
        return birthday;
    }

    //mutator for birthday
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    //accessor for account creation date
    public Date getDateCreated() {
        return dateCreated;
    }

    //mutator for account creation date
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    //check for similarity
    public boolean equals(Object obj) {
        if (obj instanceof UserDTO) {
            UserDTO other = (UserDTO) obj;
            if (other.getId().equals(id) && other.getLogin().equals(login)
                    && other.getEmail().equals(email)
                    && other.getPassword().equals(password)
                    && other.getPhone().equals(phone)
                    && other.getName().equals(name)
                    && other.getBirthday().equals(birthday)
                    && other.getDateCreated().equals(dateCreated)) {
                return true;
            }
        }

        return false;
    }

    //get entity's hash code
    public int hashCode() {
        return id.hashCode() ^ login.hashCode() ^ email.hashCode()
                ^ password.hashCode() ^ phone.hashCode() ^ name.hashCode()
                ^ birthday.hashCode() ^ dateCreated.hashCode();
    }
}
