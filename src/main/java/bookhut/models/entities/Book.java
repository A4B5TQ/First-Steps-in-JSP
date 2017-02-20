package bookhut.models.entities;

import java.util.Date;

public class Book {

    private Long id;
    private String title;
    private String Author;
    private int pages;
    private Date creationDate;
    private Date deletedOn;


    public Book() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return Author;
    }

    public int getPages() {
        return pages;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getDeletedOn() {
        return deletedOn;
    }

    public void setDeletedOn(Date deletedOn) {
        this.deletedOn = deletedOn;
    }
}
