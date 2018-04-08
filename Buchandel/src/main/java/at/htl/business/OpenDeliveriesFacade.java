package at.htl.business;

import at.htl.model.OpenDeliveries;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by cristian on 14.10.2017.
 */
@Stateless
public class OpenDeliveriesFacade {
    @PersistenceContext
    EntityManager em;

    public void create(OpenDeliveries openDeliveries){
        if(openDeliveries.getId() != null && em.find(OpenDeliveries.class, openDeliveries.getId())!= null){
            em.merge(openDeliveries);
        }
        else {
            em.persist(openDeliveries);
        }
    }
    public List<OpenDeliveries> getOpenDeliveries(){
        TypedQuery<OpenDeliveries> deliveriesTypedQuery= em.createQuery("select z from OpenDeliveries z", OpenDeliveries.class);
        return  deliveriesTypedQuery.getResultList();
    }
}
