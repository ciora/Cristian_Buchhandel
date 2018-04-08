package at.htl.business;


import at.htl.model.BookPurchases;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by cristian on 14.10.2017.
 */
@Stateless
public class BookPurchasesFacade {
    @PersistenceContext
    EntityManager em;

    public void create(BookPurchases bookPurchases){
        if(bookPurchases.getId() != null && em.find(BookPurchases.class, bookPurchases.getId())!= null){
            em.merge(bookPurchases);
        }
        else {
            em.persist(bookPurchases);
        }
    }
    public List<BookPurchases> getAllBookPurchases(){
        TypedQuery<BookPurchases> bookPurchasesTypedQuery= em.createQuery("select z from BookPurchases z", BookPurchases.class);
        return  bookPurchasesTypedQuery.getResultList();
    }
}
