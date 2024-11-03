package se.yrgo.crm.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import se.yrgo.crm.domain.Customer;
import se.yrgo.crm.services.customers.CustomerManagementService;

import java.util.List;

public class SimpleClient {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext container = new
                ClassPathXmlApplicationContext("application.xml");

        CustomerManagementService customerManagementService = container.getBean(CustomerManagementService.class);
        List<Customer>allCustomers = customerManagementService.getAllCustomers();
        for(Customer customer:allCustomers){
            System.out.println(customer);
        }
        container.close();

    }
}
