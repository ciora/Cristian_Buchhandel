package at.htl.model;

import javax.persistence.*;

@Entity
@Table
public class OpenDeliveries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String postalCode;
    private String city;
    private String houseNr;
    @OneToOne
    @JoinColumn(name = "book")
    private Book book;

    public OpenDeliveries() {
    }

    public OpenDeliveries(String street, String postalCode, String city, String houseNr, Book book) {
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.houseNr = houseNr;
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHouseNr() {
        return houseNr;
    }

    public void setHouseNr(String houseNr) {
        this.houseNr = houseNr;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
