package at.htl.business;

import at.htl.model.Book;
import at.htl.model.Customer;
import at.htl.model.Publisher;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cristian on 14.10.2017.
 */
@Singleton
@Startup
public class InitBean {

    @Inject
    BookFacade bookFacade;

    @Inject
    CustomerFacade customerFacade;

    @Inject
    PublisherFacade publisherFacade;

    public InitBean() {
    }

    @PostConstruct
    private void init(){

        Publisher goodPublisher = new Publisher("GoodPublisher","GoodPublisherStreet 1","5555","PublisherCity");
        Publisher mediumPublisher = new Publisher("MediumPublisher","MediumPublisherStreet 2","5555","PublisherCity");
        Publisher badPublisher = new Publisher("BadPusblisher","BadPusblisherStreet 3","5555","PublisherCity");

        publisherFacade.create(goodPublisher);
        publisherFacade.create(mediumPublisher);
        publisherFacade.create(badPublisher);

        Book theAdventure = new Book("The Adventure of The Miniprojekt","111111","Me",11.11,goodPublisher);
        Book theStory = new Book("NVS-The Story","222222","AlsoMe",22.22,mediumPublisher);
        Book judgementDay = new Book("SYP - The Judgementday","333333","WhoeElseThanMe",33.33,badPublisher);
        Book jdk = new Book("JDK - Judgement Day Kickoff", "444444", "HmmmmMaybeMe",44.44, badPublisher);

        bookFacade.create(theAdventure);
        bookFacade.create(theStory);
        bookFacade.create(judgementDay);
        bookFacade.create(jdk);

        List<Book> bookList = new LinkedList<Book>();
        bookList.add(theAdventure);
        bookList.add(theStory);
        bookList.add(judgementDay);
        bookList.add(jdk);

        Customer customer = new Customer("Ciora","Cristian",bookList);

        customerFacade.create(customer);
    }
}
