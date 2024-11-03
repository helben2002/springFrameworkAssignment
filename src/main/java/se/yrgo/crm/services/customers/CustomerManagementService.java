package se.yrgo.crm.services.customers;

import java.util.List;

import se.yrgo.crm.domain.Call;
import se.yrgo.crm.domain.Customer;



public interface CustomerManagementService {


	public void updateCustomer(Customer changedCustomer);


	public void deleteCustomer(Customer oldCustomer);


	void newCustomer(Customer newCustomer);

	public Customer findCustomerById(String customerId) throws CustomerNotFoundException;

	public List<Customer> findCustomersByName (String name);

	public List<Customer> getAllCustomers();


	public Customer getFullCustomerDetail(String customerId) throws CustomerNotFoundException;

	void recordCall(String customerId, Call callDetails) throws CustomerNotFoundException;
}
