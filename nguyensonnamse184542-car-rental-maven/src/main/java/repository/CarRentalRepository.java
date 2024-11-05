package repository;

import java.util.List;

import dao.CarRentalDAO;
import pojo.CarRental;
import pojo.CarRentalId;

public class CarRentalRepository implements ICarRentalRepository {
	 private CarRentalDAO carRentalDAO;

	    public CarRentalRepository(String persistenceName) {
	        this.carRentalDAO = new CarRentalDAO(persistenceName);
	    }
	@Override
	public void save(CarRental carRental) {
		// TODO Auto-generated method stub
		carRentalDAO.save(carRental);
	}

	@Override
	public List<CarRental> findByCustomerAndCarName(String email, String carName){
				return carRentalDAO.findByCustomerAndCarName(email,carName);
	}


	@Override
	public List<CarRental> getAllRentals() {
		 return carRentalDAO.getAllCars();
	}
	@Override
	public CarRental findById(CarRentalId carRentalId) {
		// TODO Auto-generated method stub
		return carRentalDAO.findById(carRentalId);
	}

	@Override
	public void update(CarRental carRental) {
		// TODO Auto-generated method stub
		carRentalDAO.update(carRental);
	}

}
