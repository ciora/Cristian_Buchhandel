package at.htl.business;


import at.htl.model.Warenkorb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by cristian on 14.10.2017.
 */
@Stateless
public class WarenKorbFacade {
    @PersistenceContext
    EntityManager em;

    public void create(Warenkorb warenkorb){
        if(warenkorb.getId() != null && em.find(Warenkorb.class, warenkorb.getId())!= null){
            em.merge(warenkorb);
        }
        else {
            em.persist(warenkorb);
        }
    }
    public List<Warenkorb> getAllWarenKorbItems(){
        TypedQuery<Warenkorb> warenkorbTypedQuery= em.createQuery("select z from Warenkorb z", Warenkorb.class);
        return  warenkorbTypedQuery.getResultList();
    }
}
