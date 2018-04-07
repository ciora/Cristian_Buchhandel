package at.htl.web;

import at.htl.model.Book;

public class WarenKorbObject {

    Book book;
    int amount;

    public WarenKorbObject() {
    }

    public WarenKorbObject(Book book, int amount) {
        this.book = book;
        this.amount = amount;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
