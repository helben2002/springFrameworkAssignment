package se.yrgo.crm.integrationtests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.crm.domain.Customer;
import se.yrgo.crm.services.customers.CustomerManagementService;
import se.yrgo.crm.services.customers.CustomerNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"/other-tiers.xml", "/datasource-test.xml"})
@Transactional
public class CustomerManagementTest {

    @Autowired
    private CustomerManagementService customerManagementService;

    @Test
    void creatingNewCustomerTest() {
        Customer customer = new Customer("AABBCC", "Yrgo", "Happy customer");
        customerManagementService.newCustomer(customer);

        assertEquals(1, customerManagementService.getAllCustomers().size(), "There should be a customer in the database");
    }

    @Test
    void findingExistingCustomerTest() throws CustomerNotFoundException {
        Customer customer = new Customer("0025", "NK", "Working hard");
        customerManagementService.newCustomer(customer);

        Customer existingCustomer = customerManagementService.findCustomerById("0025");
        assertEquals(existingCustomer, customer);
    }
}
