package at.htl.business;

import at.htl.model.Book;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by cristian on 14.10.2017.
 */
@Stateless
public class BookFacade {
    @PersistenceContext
    EntityManager em;

    public void create(Book book){
        if(book.getId() != null && em.find(Book.class, book.getId())!= null){
            em.merge(book);
        }
        else {
            em.persist(book);
        }
    }
    public List<Book> getBooks(){
        TypedQuery<Book> bookTypedQuery= em.createQuery("select z from Book z", Book.class);
        return  bookTypedQuery.getResultList();
    }
}
