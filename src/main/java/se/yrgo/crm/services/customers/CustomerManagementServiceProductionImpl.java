package se.yrgo.crm.services.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.crm.dataaccess.CustomerDao;
import se.yrgo.crm.dataaccess.RecordNotFoundException;
import se.yrgo.crm.domain.Call;
import se.yrgo.crm.domain.Customer;

import java.util.List;

@Service("customerManagement")
@Transactional
public class CustomerManagementServiceProductionImpl implements CustomerManagementService {
    @Autowired
    CustomerDao customerDao;

    @Override
    public void updateCustomer(Customer changedCustomer) throws CustomerNotFoundException {
        try {
            customerDao.update(changedCustomer);
        } catch (RecordNotFoundException e) {
            throw new CustomerNotFoundException();
        }
    }

    @Override
    public void deleteCustomer(Customer oldCustomer) throws CustomerNotFoundException {
        try {
            customerDao.delete(oldCustomer);
        } catch (RecordNotFoundException e) {
            throw new CustomerNotFoundException();
        }
    }

    @Override
    public void newCustomer(Customer newCustomer) {
        customerDao.create(newCustomer);
    }

    @Override
    public Customer findCustomerById(String customerId) throws CustomerNotFoundException {
        try {
            return customerDao.getById(customerId);
        } catch (RecordNotFoundException e) {
            throw new CustomerNotFoundException();
        }
    }

    @Override
    public List<Customer> findCustomersByName(String name) {
        return customerDao.getByName(name);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @Override
    public Customer getFullCustomerDetail(String customerId) throws CustomerNotFoundException {
        try {
            return customerDao.getFullCustomerDetail(customerId);
        } catch (RecordNotFoundException e) {
            throw new CustomerNotFoundException();
        }
    }

    @Override
    public void recordCall(String customerId, Call callDetails) throws CustomerNotFoundException {
        try {
            customerDao.addCall(callDetails, customerId);
        } catch (RecordNotFoundException e) {
            throw new CustomerNotFoundException();
        }
    }
}
