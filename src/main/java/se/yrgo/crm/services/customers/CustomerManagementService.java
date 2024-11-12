package se.yrgo.crm.services.customers;

import java.util.List;

import se.yrgo.crm.dataaccess.RecordNotFoundException;
import se.yrgo.crm.domain.Call;
import se.yrgo.crm.domain.Customer;



public interface CustomerManagementService {


	public void updateCustomer(Customer changedCustomer) throws CustomerNotFoundException;


	public void deleteCustomer(Customer oldCustomer) throws CustomerNotFoundException;


	void newCustomer(Customer newCustomer) throws CustomerNotFoundException;

	public Customer findCustomerById(String customerId) throws CustomerNotFoundException;

	public List<Customer> findCustomersByName (String name) throws CustomerNotFoundException;

	public List<Customer> getAllCustomers();


	public Customer getFullCustomerDetail(String customerId) throws CustomerNotFoundException, RecordNotFoundException;

	void recordCall(String customerId, Call callDetails) throws CustomerNotFoundException;
}
