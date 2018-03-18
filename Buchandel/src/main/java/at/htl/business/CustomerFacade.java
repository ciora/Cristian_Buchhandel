package at.htl.business;

import at.htl.model.Customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by cristian on 14.10.2017.
 */
@Stateless
public class CustomerFacade {
    @PersistenceContext
    EntityManager em;

    public void create(Customer customer){
        if(customer.getId() != null && em.find(Customer.class, customer.getId())!= null){
            em.merge(customer);
        }
        else {
            em.persist(customer);
        }
    }
    public List<Customer> getCustomers(){
        TypedQuery<Customer> customerTypedQuery= em.createQuery("select z from Customer z", Customer.class);
        return  customerTypedQuery.getResultList();
    }
}
