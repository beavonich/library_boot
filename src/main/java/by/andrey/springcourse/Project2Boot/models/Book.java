package by.andrey.springcourse.Project2Boot.models;


import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "bookId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @NotEmpty(message = "This field should not be empty")
    @Length(min = 0, max = 70, message = "Book name can contain a maximum 70 characters")
    @Column(name = "bookName")
    private String bookName;

    @NotEmpty(message = "This field should not be empty")
    @Length(min = 0, max = 70, message = "This field can contain a maximum 70 characters")
    @Column(name = "bookAuthor")
    private String bookAuthor;

    @Min(value = 1000, message = "Enter a real year")
    @Max(value = 2023, message = "Enter a real year")
    @Column(name = "year")
    private int year;

    @Column(name = "dateTaken")
    @Temporal(TemporalType.DATE)
    private Date dateTaken;

    @ManyToOne
    @JoinColumn(name = "personId", referencedColumnName = "personId")
    private Person owner;



    public Book() {
    }

    public Book(String bookName, String bookAuthor, int year) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.year = year;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Date getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(Date dateTaken) {
        this.dateTaken = dateTaken;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", year=" + year +
                '}';
    }
}
