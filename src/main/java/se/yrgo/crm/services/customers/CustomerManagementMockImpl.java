package se.yrgo.crm.services.customers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import se.yrgo.crm.domain.Call;
import se.yrgo.crm.domain.Customer;

public class CustomerManagementMockImpl implements CustomerManagementService {
	private HashMap<String,Customer> customerMap;

	public CustomerManagementMockImpl() {
		customerMap = new HashMap<String,Customer>();
		customerMap.put("OB74", new Customer("OB74" ,"Fargo Ltd", "some notes"));
		customerMap.put("NV10", new Customer("NV10" ,"North Ltd", "some other notes"));
		customerMap.put("RM210", new Customer("RM210" ,"River Ltd", "some more notes"));
	}


	@Override
	public void newCustomer(Customer newCustomer) {
		customerMap.put(newCustomer.getCustomerId(), new Customer());
	}

	@Override
	public void updateCustomer(Customer changedCustomer) {
		String customerId = changedCustomer.getCustomerId();
		customerMap.put(customerId, changedCustomer);
	}

	@Override
	public void deleteCustomer(Customer oldCustomer) {
		customerMap.remove(oldCustomer.getCustomerId());
	}

	@Override
	public Customer findCustomerById(String customerId) throws CustomerNotFoundException {
		if (customerMap.containsKey(customerId)){
        	return customerMap.get(customerId);
		} else {
			throw new CustomerNotFoundException();
		}
	}

	@Override
	public List<Customer> findCustomersByName(String name) {
		return (List<Customer>) customerMap.get(name);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return new ArrayList<Customer>(customerMap.values());
	}

	@Override
	public Customer getFullCustomerDetail(String customerId) throws CustomerNotFoundException {
		if (customerMap.containsKey(customerId)){
			return customerMap.get(customerId);
		} else {
			throw new CustomerNotFoundException();
		}
	}

	@Override
	public void recordCall(String customerId, Call callDetails) throws CustomerNotFoundException {
		if (customerMap.containsKey(customerId)){
			//First find the customer
			Customer customer = customerMap.get(customerId);
			//Call the addCall on the customer
			customer.addCall(callDetails);
		} else {
			throw new CustomerNotFoundException();
		}
	}

}
