package at.htl.business;

import at.htl.model.Publisher;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by cristian on 14.10.2017.
 */
@Stateless
public class PublisherFacade {
    @PersistenceContext
    EntityManager em;

    public void create(Publisher publisher){
        if(publisher.getId() != null && em.find(Publisher.class, publisher.getId())!= null){
            em.merge(publisher);
        }
        else {
            em.persist(publisher);
        }
    }
    public List<Publisher> getPublishers(){
        TypedQuery<Publisher> publisherTypedQuery= em.createQuery("select z from Publisher z", Publisher.class);
        return  publisherTypedQuery.getResultList();
    }
}
