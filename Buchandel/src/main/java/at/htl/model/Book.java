package at.htl.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cristian on 14.10.2017.
 */
@Table(name = "Book")
@Entity
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @NotNull
    @Size(min=6,max = 10)
    private String ISBN;
    @NotNull
    @Size(min=0,max=255)
    private String autor;
    private double price;
    private int pages;
    private String language;
    private String summary;
    private String genre;
    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BookPurchases> bookPurchases = new LinkedList<BookPurchases>();


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "publisher")
    private Publisher publisher;

    public Book() {
    }

    public Book(String title, String ISBN, String autor, double price, Publisher publisher,int pages, String language, String summary,String genre) {
        this.title = title;
        this.ISBN = ISBN;
        this.autor = autor;
        this.price = price;
        this.publisher = publisher;
        this.pages = pages;
        this.language = language;
        this.summary = summary;
        this.genre = genre;

    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        language = language;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String SSID) {
        this.ISBN = SSID;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
