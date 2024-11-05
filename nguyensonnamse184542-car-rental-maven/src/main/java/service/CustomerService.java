package service;

import java.util.List;
import pojo.Customer;
import repository.CustomerRepository;
import repository.ICustomerRepository;

public class CustomerService implements ICustomerService {
    private ICustomerRepository customerRepository;

    // Constructor to initialize the repository
    public CustomerService(String persistenceUnitName) {
        this.customerRepository = new CustomerRepository(persistenceUnitName);
    }

    // Find a customer by email
    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    // Save a new customer
    @Override
    public void save(Customer customer) {
        // Business logic before saving can be added here
        customerRepository.save(customer);
    }

    // Delete a customer by ID
    @Override
    public void delete(Integer customerId) {
        // Additional business logic can be added here (e.g., validations)
        customerRepository.delete(customerId);
    }

    // Find a customer by ID
    @Override
    public Customer findById(Integer customerId) {
        return customerRepository.findById(customerId);
    }

    // Update an existing customer
    @Override
    public void update(Customer customer) {
        // Business logic before updating can be added here (e.g., validations)
        customerRepository.update(customer);
    }

    // Retrieve all customers
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }
    @Override
    public List<Customer> findByCustomerName(String customerName) {
    	return customerRepository.findByCustomerName(customerName);
    }
}
