package at.htl.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
public class BookPurchases {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    private Date purchaseDate;
    public BookPurchases() {
    }

    public BookPurchases(Book book, Customer customer, Date purchaseDate) {
        this.book = book;
        this.customer = customer;
        this.purchaseDate = purchaseDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
