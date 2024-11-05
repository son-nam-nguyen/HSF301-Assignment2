package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import pojo.Customer;

public class CustomerDAO {
    private static EntityManagerFactory emf;

    public CustomerDAO(String persistenceName) {
        emf = Persistence.createEntityManagerFactory(persistenceName);
    }

    public Customer findByEmail(String email) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.email = :email", Customer.class);
            query.setParameter("email", email);
            return query.getResultStream().findFirst().orElse(null);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void save(Customer customer) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void delete(Integer customerId) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Customer customer = em.find(Customer.class, customerId);
            if (customer != null) {
                em.remove(customer);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Customer findById(Integer customerId) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            return em.find(Customer.class, customerId);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void update(Customer customer) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Customer existingCustomer = em.find(Customer.class, customer.getCustomerID());
            if (existingCustomer != null) {
                existingCustomer.setCustomerName(customer.getCustomerName());
                existingCustomer.setMobile(customer.getMobile());
                existingCustomer.setBirthday(customer.getBirthday());
                existingCustomer.setIdentityCard(customer.getIdentityCard());
                existingCustomer.setLicenceNumber(customer.getLicenceNumber());
                existingCustomer.setLicenceDate(customer.getLicenceDate());
                existingCustomer.setEmail(customer.getEmail());
                existingCustomer.setPassword(customer.getPassword());
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Customer> getAllCustomers() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            return em.createQuery("FROM Customer", Customer.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public Customer findByMobile(String mobile) {
        EntityManager em = null;
        Customer customer = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.mobile = :mobile", Customer.class);
            query.setParameter("mobile", mobile);
            customer = query.getResultStream().findFirst().orElse(null);
            
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return customer;
    }

    public Customer findByIdentityCard(String identityCard) {
        EntityManager em = null;
        Customer customer = null;
        try {
        	em = emf.createEntityManager();
            em.getTransaction().begin();
            
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.identityCard = :identityCard", Customer.class);
            query.setParameter("identityCard", identityCard);
            customer = query.getResultStream().findFirst().orElse(null);
            
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return customer;
    }
    
    public boolean updateCustomer(Customer customer) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();

            if (customer.getCustomerID() == null) {
                System.out.println("Error: Customer ID is null. Cannot update.");
                return false;
            }

            Customer existingCustomer = em.find(Customer.class, customer.getCustomerID());
            if (existingCustomer != null) {
                existingCustomer.setCustomerName(customer.getCustomerName());
                existingCustomer.setMobile(customer.getMobile());
                existingCustomer.setEmail(customer.getEmail());
                existingCustomer.setIdentityCard(customer.getIdentityCard());
                existingCustomer.setLicenceNumber(customer.getLicenceNumber());
                existingCustomer.setPassword(customer.getPassword());
                existingCustomer.setBirthday(customer.getBirthday());
                existingCustomer.setLicenceDate(customer.getLicenceDate());

                em.merge(existingCustomer);
                em.getTransaction().commit();
                return true;
            } else {
                System.out.println("Error: Customer with ID " + customer.getCustomerID() + " not found.");
                em.getTransaction().rollback();
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Error updating customer: " + ex.getMessage());
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public List<Customer> findByCustomerName(String customerName) {
        EntityManager em = null;
        List<Customer> customers = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            
            // Use LIKE with '%' in the query, not in the parameter
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.customerName LIKE :customerName", Customer.class);
            query.setParameter("customerName", "%" + customerName + "%");
            
            // Get the result list of matching customers
            customers = query.getResultList();
            
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return customers;
    }
}
