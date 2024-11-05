package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pojo.Car;
import pojo.CarRental;
import pojo.CarRentalId;
import pojo.Customer;

public class CarRentalDAO {
	private static EntityManager em;
	private static EntityManagerFactory emf;
	private CarDAO dao;
	private CustomerDAO cusDao;
	public  CarRentalDAO(String persistanceName) {
		emf = Persistence.createEntityManagerFactory(persistanceName);
	}

	public void save(CarRental student) {
		try {
			
			em=emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(student);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Error"+e.getMessage());
		}finally {
			em.close();
		}
	};
	
	
    

    
    public void update(CarRental carRental) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            CarRentalId carRentalId = new CarRentalId(carRental.getCar().getCarID(), carRental.getCustomer().getCustomerID());
            CarRental existingCarRental = findById(carRentalId);

            if (existingCarRental != null) {
                existingCarRental.setPickupDate(carRental.getPickupDate());
                existingCarRental.setReturnDate(carRental.getReturnDate());
                existingCarRental.setRentPrice(carRental.getRentPrice());
                existingCarRental.setStatus(carRental.getStatus());
                em.merge(existingCarRental);
            } else {
                System.out.println("CarRental not found for update with ID: " + carRentalId.getCustomer() + ", " + carRentalId.getCar());
            }
            
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); 
            }
            System.out.println("Error updating CarRental: " + e.getMessage());
        } finally {
            em.close(); 
        }
    }

    
    
    public CarRental findById(CarRentalId carRentalId) {
        EntityManager em = null;
        CarRental rental = null; 
        try {
            em = emf.createEntityManager();
            rental = em.createQuery("SELECT r FROM CarRental r " +
                    "WHERE r.car = :carId AND r.customer = :customerId", 
                    CarRental.class)
            .setParameter("carId", carRentalId.getCar()) 
            .setParameter("customerId", carRentalId.getCustomer()) 
            .getSingleResult(); 
         
        } catch (Exception e) {
            System.err.println("Error occurred while finding rental: " + e.getMessage());
            
            e.printStackTrace(); 
        } finally {
            if (em != null) {
                em.close(); 
            }
        }
        return rental; 
    }


	
	
	public List<CarRental> findByCustomerAndCarName(String email, String carName) {
	    List<CarRental> rentals = null;
	    EntityManager em = null;
	    Car car = dao.findByCarName(carName);
	    Customer customer = cusDao.findByEmail(email);
	    Integer carId = car.getCarID();
	    Integer emailCus = customer.getCustomerID();
	    try {
	        em = emf.createEntityManager();
	        	        rentals = em.createQuery("SELECT cr FROM CarRental cr WHERE cr.CarID = :carId AND cr.CustomerID = :emailCus", CarRental.class)
	                    .setParameter("customerName", emailCus)
	                    .setParameter("carName", carId)
	                    .getResultList();
	                    
	    } catch (Exception e) {
	        System.out.println("Error: " + e.getMessage());
	    } finally {
	        if (em != null) {
	            em.close();
	        }
	    }
	    
	    return rentals;
	}

	
	public List<CarRental> getAllCars(){
		List<CarRental> carRentals	=null;
		try {
			em= emf.createEntityManager();
			em.getTransaction().begin();
			 carRentals = em.createQuery(
			            "SELECT cr FROM CarRental cr " +
			            "JOIN FETCH cr.customer c " +
			            "JOIN FETCH cr.car car", CarRental.class)
			            .getResultList();
			        em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error"+e.getMessage());
		}finally {em.close();}
		return carRentals;
	};
	
}
