package at.htl.web;

import at.htl.business.*;
import at.htl.model.*;
import org.primefaces.event.SelectEvent;
import sun.awt.image.ImageWatched;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Model;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Calendar;
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

    @Inject
    OpenDeliveriesFacade openDeliveriesFacade;

    @Inject
    BookPurchasesFacade bookPurchasesFacade;

    List<Book> books = new LinkedList<Book>();

    Book selectedBook = new Book();

    Book actBook = new Book();

    List<Book> warenKorb = new LinkedList<Book>();

    List<String> genres = new LinkedList<String>();

    List<Book> filteredBookList = new LinkedList<Book>();

    String genre;

    String titleToFilter;

    OpenDeliveries deliveriy = new OpenDeliveries();

    List<WarenKorbObject> warenKorbObjectList = new LinkedList<WarenKorbObject>();
    public IndexController() {

    }

    @PostConstruct
    public void init(){
        this.books = bookFacade.getBooks();
        filteredBookList = books;
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

    public List<Book> getFilteredBookList() {
        return filteredBookList;
    }

    public void setFilteredBookList(List<Book> filteredBookList) {
        this.filteredBookList = filteredBookList;
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
            if(warenKorbObject.getBook().getId() == toAddBook.getId()){
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
            if(warenKorbObject.getBook().getId() == actWarenKorbItem.getBook().getId()){
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

    public List<String> getGenres() {
        boolean genreFound;
        for (Book book:books
                ) {
            genreFound = false;
            for (int i = 0; i < genres.size(); i++) {
                if(genres.get(i).equals(book.getGenre())){
                    genreFound = true;
                }
            }
            if(genreFound == false){
                genres.add(book.getGenre());
            }
        }
        return genres;
    }


    
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitleToFilter() {
        return titleToFilter;
    }

    public void setTitleToFilter(String titleToFilter) {
        this.titleToFilter = titleToFilter;
    }

    public OpenDeliveries getDeliveriy() {
        return deliveriy;
    }

    public void setDeliveriy(OpenDeliveries deliveriy) {
        this.deliveriy = deliveriy;
    }

    public void onGenreChanged(){
        filteredBookList = new LinkedList<Book>();
        for (Book book:books
             ) {
            if(genre == null){
                filteredBookList = books;
                filterBooksByTitle();
            }
            else{
                if(titleToFilter == null || titleToFilter.equals("")){
                    if(book.getGenre().equals(genre)){
                        filteredBookList.add(book);
                    }
                }else{
                    if(book.getGenre().equals(genre) && book.getTitle().contains(titleToFilter)){
                        filteredBookList.add(book);
                    }
                }
            }
        }
    }

    public void  filterBooksByTitle(){
        List<Book> actShownList = filteredBookList;
        filteredBookList = new LinkedList<Book>();
        if(titleToFilter.equals("")) {
            filteredBookList = bookFacade.getBooks();
        }
        else{
            for (Book book:books
                    ) {
                if(genre != null){
                    if(book.getTitle().contains(titleToFilter) && book.getGenre().equals(genre)){
                        filteredBookList.add(book);
                    }
                }else {
                    if(book.getTitle().contains(titleToFilter)){
                        filteredBookList.add(book);
                    }
                }
            }
        }
    }

    public void addNewDelivery(){
        List<Customer> customers = customerFacade.getCustomers();
        for (WarenKorbObject warenKorbItem: warenKorbObjectList
             ) {
            for (int i = 0; i < warenKorbItem.getAmount(); i++) {
                openDeliveriesFacade.create(new OpenDeliveries(deliveriy.getStreet(),
                        deliveriy.getPostalCode(),
                        deliveriy.getCity(),
                        deliveriy.getHouseNr(),
                        warenKorbItem.getBook()));

                java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                bookPurchasesFacade.create(new BookPurchases(warenKorbItem.getBook(),customers.get(0),date));
            }
        }
        warenKorbObjectList.clear();
    }
}
