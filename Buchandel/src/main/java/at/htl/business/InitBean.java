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
 Book theAdventure1 = new Book("Das Lied der Dunkelheit","111111","Peter V. Brett",11.11,goodPublisher,345,"German",summary,"Sci-Fi");
        Book theStory1 = new Book("Das Flüstern der Nacht","222222","Peter V. Brett",22.22,mediumPublisher,153,"German",summary,"Fantasy");
        Book judgementDay1 = new Book("Die Flammen der Dämmerung","333333","Peter V. Brett",33.33,badPublisher,434,"English",summary,"History");
        Book jdk1 = new Book("Der Weg der Könige", "444444", "Brandon Sanderson",44.44, badPublisher,371,"German",summary,"Biography");
        Book theStory2 = new Book("Der Pfad der Winde","222222","Brandon Sanderson",22.22,mediumPublisher,153,"German",summary,"Fantasy");
        Book judgementDay2 = new Book("Word of Radiance","333333","Brandon Sanderson",33.33,badPublisher,456,"English",summary,"Fantasy");
        Book jdk2 = new Book("Oathbringer", "444444", "Brandon Sanderson",44.44, badPublisher,947,"German",summary,"History");
        Book theAdventure2 = new Book("Kinder des Nebels","111111","Brandon Sanderson",11.11,goodPublisher,156,"German",summary,"Biography");
        Book theStory3 = new Book("Krieder des Feuers","222222","Brandon Sanderson",22.22,mediumPublisher,153,"German",summary,"Fantasy");
        Book judgementDay3 = new Book("Herrscher des Lichts","333333","Brandon Sanderson",33.33,badPublisher,678,"English",summary,"Fantasy");
        Book jdk3 = new Book("Die Furcht der Weisen 1", "444444", "Patrick Rothfuss",44.44, badPublisher,354,"German",summary,"Fantasy");
        Book theAdventure3 = new Book("Die Furcht der Weisen 2","111111","Patrick Rothfuss",11.11,goodPublisher,478,"English",summary,"Sci-Fi");
        Book theStory4 = new Book("Der Name des Windes","222222","Patrick Rothfuss",22.22,mediumPublisher,213,"German",summary,"Fantasy");
        Book theAdventure = new Book("The Adventure of The Miniprojekt","111111","Me",11.11,goodPublisher,153,"German",summary,"Fantasy");
        Book theStory = new Book("NVS-The Story","222222","AlsoMe",22.22,mediumPublisher,812,"German",summary,"Sci-Fi");
        Book judgementDay = new Book("SYP - The Judgementday","333333","WhoeElseThanMe",33.33,badPublisher,456,"English",summary,"History");
        Book jdk = new Book("JDK - Judgement Day Kickoff", "444444", "HmmmmMaybeMe",44.44, badPublisher,124,"German",summary,"Biography");



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

        List<Book> bookList = new LinkedList<Book>();
        bookList.add(theAdventure);
        bookList.add(theStory);
        bookList.add(judgementDay);
        bookList.add(jdk);

        Customer customer = new Customer("Foo","Paul",bookList);

        customerFacade.create(customer);
    }
}
