package repository;

import dao.CustomerDAO;
import pojo.Customer;
import java.util.List;

public class CustomerRepository implements ICustomerRepository {
    private CustomerDAO customerDAO;

    // Constructor to initialize the DAO with the persistence unit name
    public CustomerRepository(String persistenceUnitName) {
        this.customerDAO = new CustomerDAO(persistenceUnitName);
    }

    // Method to find a customer by email
    @Override
    public Customer findByEmail(String email) {
        return customerDAO.findByEmail(email);
    }

    // Method to save a customer
    @Override
    public void save(Customer customer) {
        customerDAO.save(customer);
    }

    // Method to delete a customer by ID
    @Override
    public void delete(Integer customerId) {
        customerDAO.delete(customerId);
    }

    // Method to find a customer by ID
    @Override
    public Customer findById(Integer customerId) {
        return customerDAO.findById(customerId);
    }

    // Method to update a customer
    @Override
    public void update(Customer customer) {
        customerDAO.update(customer);
    }

    // Method to get all customers
    @Override
    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }
	@Override
	public boolean updateCustomer(Customer customer) {
		return customerDAO.updateCustomer(customer);
	}

	@Override
	public Customer findByMobile(String mobile) {
		// TODO Auto-generated method stub
		return customerDAO.findByMobile(mobile);
	}

	@Override
	public Customer findByIdentityCard(String identityCard) {
		// TODO Auto-generated method stub
		return customerDAO.findByIdentityCard(identityCard);
	}
	@Override
    public List<Customer> findByCustomerName(String customerName) {
return customerDAO.findByCustomerName(customerName);
	}
}
