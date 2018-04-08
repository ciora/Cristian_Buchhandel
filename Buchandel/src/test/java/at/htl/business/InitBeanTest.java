package at.htl.business;

import at.htl.model.Book;
import at.htl.model.BookPurchases;
import at.htl.model.Customer;
import at.htl.model.Publisher;
import org.junit.Test;

import javax.persistence.Persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

import static org.junit.Assert.*;

public class InitBeanTest {
    BookFacade bookFacade = new BookFacade();

    BookPurchasesFacade purchasesFacade = new BookPurchasesFacade();
    public InitBeanTest() {
        bookFacade.em = Persistence.createEntityManagerFactory("myTestPU").createEntityManager();
        purchasesFacade.em = Persistence.createEntityManagerFactory("myTestPU").createEntityManager();
    }

    @Test
    public void t010_CreateBook() {


        Publisher goodPublisher = new Publisher("GoodPublisher","GoodPublisherStreet 1","5555","PublisherCity");
        Publisher mediumPublisher = new Publisher("MediumPublisher","MediumPublisherStreet 2","5555","PublisherCity");
        Publisher badPublisher = new Publisher("BadPusblisher","BadPusblisherStreet 3","5555","PublisherCity");


        String summary = "This Field should contain a short summary about the book. Sadly it is not available at the moment. " +
                "In the near future this problem will be solved " +
                "so that you can enjoy the service of this bookshop to the fullest.";

        for (int i = 0; i < 9; i++) {
            bookFacade.em.getTransaction().begin();
            Book testBook = new Book("TestBook"+i,"111111"+ i,"testAutor",11.11 + i,goodPublisher,153+ i*10,"TestLanguage"+i,summary,"TestGenre"+i);
            bookFacade.create(testBook);
            bookFacade.em.getTransaction().commit();
            List<String> books = (bookFacade.em.createNativeQuery("Select b.TITLE from BOOK b").getResultList());
            System.out.println(books.get(books.size() - 1));
        }
    }

    @Test
    public void t020_CreatePurchase(){
        purchasesFacade.em.getTransaction().begin();
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        List<Book> books = bookFacade.getBooks();
        Customer customer = new Customer("Ciora","Cristian",books);
        for (int i = 0; i < books.size(); i++) {
            BookPurchases purchase = new BookPurchases(books.get(i),customer,date);
            purchasesFacade.create(purchase);
            purchasesFacade.em.getTransaction().commit();
            System.out.println(purchasesFacade.em.createNativeQuery("Select p.PURCHASEDATE from BOOKPURCHASES p").getFirstResult());
        }
    }


}