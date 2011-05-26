package vn.com.onlinebookstore.dto;

import java.util.Date;

public class CommentDTO implements java.io.Serializable {

    //declare fields for Comment entity
    private Long id;    //id
    private Integer rating;    //rating
    private String content; //content
    private Long userId;    //user id (foreign key)
    private Long bookId;    //book id (foreign key)
    private Date date;  //comment date

    //accessor for id
    public Long getId() {
        return id;
    }

    //mutator for id
    public void setId(Long id) {
        this.id = id;
    }

    //accessor for rating
    public Integer getRating() {
        return rating;
    }

    //mutator for rating
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    //accessor for content
    public String getContent() {
        return content;
    }

    //mutator for content
    public void setContent(String content) {
        this.content = content;
    }

    //accessor for user id
    public Long getUserId() {
        return userId;
    }

    //mutator for user id
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    //accessor for book id
    public Long getBookId() {
        return bookId;
    }

    //mutator for book id
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    //accessor for comment date
    public Date getDate() {
        return date;
    }

    //mutator for comment date
    public void setDate(Date date) {
        this.date = date;
    }

    //check for similarity
    public boolean equals(Object obj) {
        if (obj instanceof CommentDTO) {
            CommentDTO other = (CommentDTO) obj;
            if (other.getId().equals(id) && other.getRating().equals(rating)
                    && other.getContent().equals(content)
                    && other.getUserId().equals(userId)
                    && other.getBookId().equals(bookId)
                    && other.getDate().equals(date)) {
                return true;
            }
        }

        return false;
    }

    //get entity's hash code
    public int hashCode() {
        return id.hashCode() ^ rating.hashCode() ^ content.hashCode()
                ^ userId.hashCode() ^ bookId.hashCode()
                ^ date.hashCode();
    }
}
