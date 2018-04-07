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
        String summary = "This Field should contain a short summary about the book. Sadly it is not available at the moment. " +
                "In the near future this problem will be solved " +
                "so that you can enjoy the service of this bookshop to the fullest.";
        Book theAdventure = new Book("The Adventure of The Miniprojekt","111111","Me",11.11,goodPublisher,153,"German",summary,"Fantasy");
        Book theStory = new Book("NVS-The Story","222222","AlsoMe",22.22,mediumPublisher,812,"German",summary,"Sci-Fi");
        Book judgementDay = new Book("SYP - The Judgementday","333333","WhoeElseThanMe",33.33,badPublisher,456,"English",summary,"History");
        Book jdk = new Book("JDK - Judgement Day Kickoff", "444444", "HmmmmMaybeMe",44.44, badPublisher,124,"German",summary,"Biography");
        Book theAdventure1 = new Book("The Adventure of The Miniprojekt","111111","Me",11.11,goodPublisher,345,"German",summary,"Sci-Fi");
        Book theStory1 = new Book("NVS-The Story","222222","AlsoMe",22.22,mediumPublisher,153,"German",summary,"Fantasy");
        Book judgementDay1 = new Book("SYP - The Judgementday","333333","WhoeElseThanMe",33.33,badPublisher,434,"English",summary,"History");
        Book jdk1 = new Book("JDK - Judgement Day Kickoff", "444444", "HmmmmMaybeMe",44.44, badPublisher,371,"German",summary,"Biography");
        Book theStory2 = new Book("NVS-The Story","222222","AlsoMe",22.22,mediumPublisher,153,"German",summary,"Fantasy");
        Book judgementDay2 = new Book("SYP - The Judgementday","333333","WhoeElseThanMe",33.33,badPublisher,456,"English",summary,"Fantasy");
        Book jdk2 = new Book("JDK - Judgement Day Kickoff", "444444", "HmmmmMaybeMe",44.44, badPublisher,947,"German",summary,"History");
        Book theAdventure2 = new Book("The Adventure of The Miniprojekt","111111","Me",11.11,goodPublisher,156,"German",summary,"Biography");
        Book theStory3 = new Book("NVS-The Story","222222","AlsoMe",22.22,mediumPublisher,153,"German",summary,"Fantasy");
        Book judgementDay3 = new Book("SYP - The Judgementday","333333","WhoeElseThanMe",33.33,badPublisher,678,"English",summary,"Fantasy");
        Book jdk3 = new Book("JDK - Judgement Day Kickoff", "444444", "HmmmmMaybeMe",44.44, badPublisher,354,"German",summary,"Fantasy");
        Book theAdventure3 = new Book("The Adventure of The Miniprojekt","111111","Me",11.11,goodPublisher,478,"English",summary,"Sci-Fi");
        Book theStory4 = new Book("NVS-The Story","222222","AlsoMe",22.22,mediumPublisher,213,"German",summary,"Fantasy");
        Book judgementDay4 = new Book("SYP - The Judgementday","333333","WhoeElseThanMe",33.33,badPublisher,821,"English",summary,"History");
        Book jdk4 = new Book("JDK - Judgement Day Kickoff", "444444", "HmmmmMaybeMe",44.44, badPublisher,234,"German",summary,"Sci-Fi");


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
