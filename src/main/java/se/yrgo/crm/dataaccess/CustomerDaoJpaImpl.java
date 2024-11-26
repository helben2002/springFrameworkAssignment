package se.yrgo.crm.dataaccess;



import org.springframework.stereotype.Repository;
import se.yrgo.crm.domain.Call;
import se.yrgo.crm.domain.Customer;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CustomerDaoJpaImpl implements CustomerDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Customer customer) {
        em.persist(customer);
    }

    @Override
    public Customer getById(String customerId) throws RecordNotFoundException {
        try {
            return em.find(Customer.class, customerId);
        }catch (javax.persistence.NoResultException e) {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public List<Customer> getByName(String name) {
        return em.createQuery("select customer from Customer as customer where " +
                "customer.name=:name" , Customer.class).setParameter("name", name)
                .getResultList();
    }

    @Override
    public void update(Customer customerToUpdate) throws RecordNotFoundException {
        Customer customer = em.find(Customer.class, customerToUpdate.getCustomerId());

        if (customer == null) {
            throw new RecordNotFoundException();
        }

        customer.setTelephone(customerToUpdate.getTelephone());
        customer.setEmail(customerToUpdate.getEmail());
        customer.setCompanyName(customerToUpdate.getCompanyName());
        customer.setNotes(customerToUpdate.getNotes());
        customer.setCalls(customerToUpdate.getCalls());
    }

    @Override
    public void delete(Customer oldCustomer) throws RecordNotFoundException {
        try {
            Customer customer = em.find(Customer.class, oldCustomer);
            em.remove(customer);
        }catch (javax.persistence.NoResultException e) {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return em.createQuery("from Customer", Customer.class).getResultList();
    }

    @Override
    public Customer getFullCustomerDetail(String customerId) throws RecordNotFoundException {
        try {
            return em.createQuery("select customer from Customer as customer" +
                    "left join fetch customer.calls where customer.customerId =:customerId",
                    Customer.class).setParameter("customerId", customerId).getSingleResult();
        }catch (javax.persistence.NoResultException e) {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void addCall(Call newCall, String customerId) throws RecordNotFoundException {
        try {
            Customer customer = em.find(Customer.class, customerId);
            customer.addCall(newCall);
        }catch (javax.persistence.NoResultException e) {
            throw new RecordNotFoundException();
        }
    }
}
