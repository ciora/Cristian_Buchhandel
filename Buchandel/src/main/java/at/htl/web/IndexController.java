package at.htl.web;

import at.htl.business.BookFacade;
import at.htl.business.CustomerFacade;
import at.htl.business.PublisherFacade;
import at.htl.model.Book;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Model;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Named
@ApplicationScoped
public class IndexController implements Serializable {

    @Inject
    BookFacade bookFacade;

    @Inject
    PublisherFacade publisherFacade;

    @Inject
    CustomerFacade customerFacade;


    List<Book> books;

    Book selectedBook = new Book();

    Book actBook = new Book();

    List<Book> warenKorb = new LinkedList<Book>();

    List<String> genres = new LinkedList<String>();

    List<WarenKorbObject> warenKorbObjectList = new LinkedList<WarenKorbObject>();
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

    public Book getActBook() {
        return actBook;
    }

    public void setActBook(Book actBook) {
        this.actBook = actBook;
    }

    public List<Book> getWarenKorb() {
        return warenKorb;
    }

    public void setWarenKorb(List<Book> warenKorb) {
        this.warenKorb = warenKorb;
    }

    public List<WarenKorbObject> getWarenKorbObjectList() {
        return warenKorbObjectList;
    }

    public void setWarenKorbObjectList(List<WarenKorbObject> warenKorbObjectList) {
        this.warenKorbObjectList = warenKorbObjectList;
    }

    public void addBookToWarenKorb(Book toAddBook){
        boolean firstTimeRegister= true;
        for (WarenKorbObject warenKorbObject:warenKorbObjectList
             ) {
            if(warenKorbObject.getBook().getTitle().equals(toAddBook.getTitle())){
                warenKorbObject.setAmount(warenKorbObject.amount + 1);
                firstTimeRegister = false;
            }
        }
        if(firstTimeRegister ==true){
            warenKorbObjectList.add(new WarenKorbObject(toAddBook,1));
        }

    }

    public  void  updateWarenKorbBookAmount(WarenKorbObject actWarenKorbItem,String operation){
        for (WarenKorbObject warenKorbObject:warenKorbObjectList
             ) {
            if(actWarenKorbItem.getBook().getTitle().equals(warenKorbObject.getBook().getTitle())){
                if(operation.equals("plus")){
                    warenKorbObject.setAmount(warenKorbObject.getAmount() + 1);
                }
                else{
                    int amountAfterDecrease = warenKorbObject.getAmount() - 1;
                    if(amountAfterDecrease <= 0){
                        warenKorbObjectList.remove(warenKorbObject);
                    }
                    else {
                        warenKorbObject.setAmount(warenKorbObject.getAmount() - 1);
                    }
                }

            }
        }
    }
    public  void  deleteWarenKorbItem(WarenKorbObject actWarenKorbItem){
        warenKorbObjectList.remove(actWarenKorbItem);
    }
}
