package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import pojo.Car;
import pojo.Customer;


public class CarDAO {
	private static EntityManager em;
	private static EntityManagerFactory emf;

	public  CarDAO(String persistanceName) {
		emf = Persistence.createEntityManagerFactory(persistanceName);
	}

	public void save(Car student) {
		try {
			em=emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(student);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Error"+e.getMessage());
		}finally {
			em.close();
		}
	};
	
	
	public void delete(Integer studentID) {
		try {
			em= emf.createEntityManager();
			em.getTransaction().begin();
			Car s=em.find(Car.class, studentID);
			em.remove(s);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			System.out.println("Error"+e.getMessage());
		}finally {em.close();}
		
	}
	
	public Car findById(Integer studentID) {
		Car student=null;
		try {
			em =emf.createEntityManager();
			em.getTransaction().begin();
			student =em.find(Car.class, studentID);
		} catch (Exception e) {
			System.out.println("Error"+e.getMessage());
		} finally {em.close();}
		return student;}
	
	public void update(Car student) {
		try {
			em= emf.createEntityManager();
			em.getTransaction().begin();
			Car s =em.find(Car.class, student.getCarID());
			if(s != null) {
				s.setCapacity(student.getCapacity());
				s.setCarModelYear(student.getCarModelYear());
				s.setCarName(student.getCarName());
				s.setColor(student.getColor());
				s.setRentPrice(student.getRentPrice());
				s.setStatus(student.getStatus());
				s.setProducerID(student.getProducerID());
				em.getTransaction().commit();
			}
			
			
		} catch (Exception e) {
			System.out.println("Error"+e.getMessage());
		}finally {em.close();}
		
	};
	
	public List<Car> getAllCars(){
		List<Car> students	=null;
		try {
			em= emf.createEntityManager();
			em.getTransaction().begin();
			students = em.createQuery("from Car").getResultList();
			
		} catch (Exception e) {
			System.out.println("Error"+e.getMessage());
		}finally {em.close();}
		return students;
	};
	  public Car findByCarName(String carName) {
	        Car car = null;
	        EntityManager em = null;
	        try {
	            em = emf.createEntityManager();
	            em.getTransaction().begin();
	            
	            // Use JPQL to find the customer by email
	            TypedQuery<Car> query = em.createQuery("SELECT c FROM Car c WHERE c.carName = :carName", Car.class);
	            query.setParameter("carName", carName);
	            car = query.getResultStream().findFirst().orElse(null); // Safely get the first result or null
	            
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
	        return car;
	    }
}

