package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import pojo.Account;

public class AccountDAO {
	 private static EntityManagerFactory emf;

	    public AccountDAO(String persistenceName) {
	        emf = Persistence.createEntityManagerFactory(persistenceName);
	    }

	    public Account findByRole(String role) {
	        EntityManager em = null;
	        try {
	            em = emf.createEntityManager();
	            TypedQuery<Account> query = em.createQuery("SELECT a FROM Account a WHERE a.role = :role", Account.class);
	            query.setParameter("role", role); // Corrected parameter name
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
}
