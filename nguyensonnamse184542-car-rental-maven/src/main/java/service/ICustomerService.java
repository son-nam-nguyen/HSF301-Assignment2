package service;

import java.util.List;

import pojo.Customer;

public interface ICustomerService {
	    Customer findByEmail(String email);
	    void save(Customer customer);
	    void delete(Integer customerId);
	    Customer findById(Integer customerId);
	    void update(Customer customer);
	    List<Customer> getAllCustomers();
	    public List<Customer> findByCustomerName(String customerName);

}

