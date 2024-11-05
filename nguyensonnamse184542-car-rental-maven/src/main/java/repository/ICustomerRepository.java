package repository;

import java.util.List;

import pojo.Customer;

public interface ICustomerRepository {
	public Customer findByEmail(String email);
	void save(Customer customer);
    void delete(Integer customerId);
    Customer findById(Integer customerId);
    void update(Customer customer);
    List<Customer> getAllCustomers();
    public Customer findByMobile(String mobile);
	
   	public Customer findByIdentityCard(String identityCard);
   	
   	public boolean updateCustomer(Customer customer);
    public List<Customer> findByCustomerName(String customerName);

}
