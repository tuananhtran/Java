package vn.com.onlinebookstore.dto;

import java.util.Date;

public class BookDTO implements java.io.Serializable {

    //declare fields for Book entity
    private Long id;    //id
    private String title;   //title
    private String description; //description
    private String author;  //author name
    private String publisher;   //publisher name
    private Date datePublished; //publishing date
    private Double price;   //unit price
    private String photo;   //photo url
    private Long ratingValue;    //total rating value
    private Long ratingCount;   //total rating count
    private Long categoryId;    //category id (foreign key)

    //accessor for id
    public Long getId() {
        return id;
    }

    //mutator for id
    public void setId(Long id) {
        this.id = id;
    }

    //accessor for title
    public String getTitle() {
        return title;
    }

    //mutator for title
    public void setTitle(String title) {
        this.title = title;
    }

    //accessor for description
    public String getDescription() {
        return description;
    }

    //mutator for description
    public void setDescription(String description) {
        this.description = description;
    }

    //accessor for author name
    public String getAuthor() {
        return author;
    }

    //mutator for author name
    public void setAuthor(String author) {
        this.author = author;
    }

    //accessor for publisher name
    public String getPublisher() {
        return publisher;
    }

    //mutator for publisher name
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    //accessor for publishing date
    public Date getDatePublished() {
        return datePublished;
    }

    //mutator for publishing date
    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    //accessor for unit price
    public Double getPrice() {
        return price;
    }

    //mutator for unit price
    public void setPrice(Double price) {
        this.price = price;
    }

    //accessor for photo url
    public String getPhoto() {
        return photo;
    }

    //mutator for photo url
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    //accessor for total rating value
    public Long getRatingValue() {
        return ratingValue;
    }

    //mutator for total rating value
    public void setRatingValue(Long ratingValue) {
        this.ratingValue = ratingValue;
    }

    //accessor for total rating count
    public Long getRatingCount() {
        return ratingCount;
    }

    //mutator for total rating count
    public void setRatingCount(Long ratingCount) {
        this.ratingCount = ratingCount;
    }

    //accessor for category id
    public Long getCategoryId() {
        return categoryId;
    }

    //mutator for category id
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    //check for similarity
    public boolean equals(Object obj) {
        if (obj instanceof BookDTO) {
            BookDTO other = (BookDTO) obj;
            if (other.getId().equals(id) && other.getTitle().equals(title)
                    && other.getDescription().equals(description)
                    && other.getAuthor().equals(author)
                    && other.getPublisher().equals(publisher)
                    && other.getDatePublished().equals(datePublished)
                    && other.getPrice().equals(price)
                    && other.getPhoto().equals(photo)
                    && other.getRatingValue().equals(ratingValue)
                    && other.getRatingCount().equals(ratingCount)
                    && other.getCategoryId().equals(categoryId)) {
                return true;
            }
        }

        return false;
    }

    //get entity's hash code
    public int hashCode() {
        return id.hashCode() ^ title.hashCode() ^ description.hashCode()
                ^ author.hashCode() ^ publisher.hashCode()
                ^ datePublished.hashCode() ^ price.hashCode()
                ^ photo.hashCode() ^ ratingValue.hashCode()
                ^ ratingCount.hashCode() ^ categoryId.hashCode();
    }
}
