package at.htl.web;

import at.htl.business.BookFacade;
import at.htl.business.CustomerFacade;
import at.htl.business.PublisherFacade;
import at.htl.model.Book;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Named
@ViewScoped
public class IndexController implements Serializable {

    @Inject
    BookFacade bookFacade;

    @Inject
    PublisherFacade publisherFacade;

    @Inject
    CustomerFacade customerFacade;

    List<Book> books;

    Book selectedBook = new Book();

    List<String> genres = new LinkedList<String>();
    public IndexController() {

    }

    @PostConstruct
    public void init(){
        genres.add("Fantasy");
        genres.add("Finction");
        genres.add("Sachbuch");
        genres.add("Roman");
        genres.add("Krimi");
    }

    public List<Book> getBooks() {
        return bookFacade.getBooks();
    }

    public void setBooks(List<Book> books) {
        this.books = bookFacade.getBooks();
    }

    public Book getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(Book selectedBook) {
        this.selectedBook = selectedBook;
    }
    public void onRowDblClckSelect(final SelectEvent event) {
        selectedBook = (Book) event.getObject();
        System.out.println(selectedBook.getTitle());
    }
}
