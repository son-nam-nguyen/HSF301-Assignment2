package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import pojo.CarProducer;

public class CarProducerDAO {
	private static EntityManager em;
	private static EntityManagerFactory emf;

	public  CarProducerDAO(String persistanceName) {
		emf = Persistence.createEntityManagerFactory(persistanceName);
	}

	public CarProducer findByProducerName(String producerName) {
		CarProducer director = null;
	    try {
	        em = emf.createEntityManager();
	        em.getTransaction().begin();
	        
	        // Use a JPQL query to find by name
	        director = em.createQuery("SELECT d FROM CarProducer d WHERE d.producerName = :producerName", CarProducer.class)
	                .setParameter("producerName", producerName) // Ensure the parameter matches
	                .getSingleResult();
	                      
	    } catch (NoResultException e) {
	        System.out.println("No director found with the name: " + producerName);
	    } catch (Exception ex) {
	        System.out.println("Error: " + ex.getMessage());
	    } finally {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().commit(); 
	        }
	        em.close();
	    }
	    return director;
	}
	
	public List<CarProducer> getAllCarProducer(){
		List<CarProducer> students=null;
		try {
			em= emf.createEntityManager();
			em.getTransaction().begin();
			students = em.createQuery("from CarProducer").getResultList();
			
		} catch (Exception e) {
			System.out.println("Error"+e.getMessage());
		}finally {em.close();}
		return students;
	};
}
