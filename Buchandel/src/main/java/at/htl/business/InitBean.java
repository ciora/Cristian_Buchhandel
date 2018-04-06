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
        Book theAdventure1 = new Book("The Adventure of The Miniprojekt","111111","Me",11.11,goodPublisher);
        Book theStory1 = new Book("NVS-The Story","222222","AlsoMe",22.22,mediumPublisher);
        Book judgementDay1 = new Book("SYP - The Judgementday","333333","WhoeElseThanMe",33.33,badPublisher);
        Book jdk1 = new Book("JDK - Judgement Day Kickoff", "444444", "HmmmmMaybeMe",44.44, badPublisher);
        Book theStory2 = new Book("NVS-The Story","222222","AlsoMe",22.22,mediumPublisher);
        Book judgementDay2 = new Book("SYP - The Judgementday","333333","WhoeElseThanMe",33.33,badPublisher);
        Book jdk2 = new Book("JDK - Judgement Day Kickoff", "444444", "HmmmmMaybeMe",44.44, badPublisher);
        Book theAdventure2 = new Book("The Adventure of The Miniprojekt","111111","Me",11.11,goodPublisher);
        Book theStory3 = new Book("NVS-The Story","222222","AlsoMe",22.22,mediumPublisher);
        Book judgementDay3 = new Book("SYP - The Judgementday","333333","WhoeElseThanMe",33.33,badPublisher);
        Book jdk3 = new Book("JDK - Judgement Day Kickoff", "444444", "HmmmmMaybeMe",44.44, badPublisher);
        Book theAdventure3 = new Book("The Adventure of The Miniprojekt","111111","Me",11.11,goodPublisher);
        Book theStory4 = new Book("NVS-The Story","222222","AlsoMe",22.22,mediumPublisher);
        Book judgementDay4 = new Book("SYP - The Judgementday","333333","WhoeElseThanMe",33.33,badPublisher);
        Book jdk4 = new Book("JDK - Judgement Day Kickoff", "444444", "HmmmmMaybeMe",44.44, badPublisher);


        bookFacade.create(theAdventure);
        bookFacade.create(theStory);
        bookFacade.create(judgementDay);
        bookFacade.create(jdk);
        bookFacade.create(theAdventure1);
        bookFacade.create(theStory1);
        bookFacade.create(judgementDay1);
        bookFacade.create(jdk1);
        bookFacade.create(theAdventure2);
        bookFacade.create(theStory2);
        bookFacade.create(judgementDay2);
        bookFacade.create(jdk2);
        bookFacade.create(theAdventure3);
        bookFacade.create(theStory3);
        bookFacade.create(judgementDay3);
        bookFacade.create(jdk3);
        bookFacade.create(theStory4);
        bookFacade.create(judgementDay4);
        bookFacade.create(jdk4);
        List<Book> bookList = new LinkedList<Book>();
        bookList.add(theAdventure);
        bookList.add(theStory);
        bookList.add(judgementDay);
        bookList.add(jdk);

        Customer customer = new Customer("Ciora","Cristian",bookList);

        customerFacade.create(customer);
    }
}
