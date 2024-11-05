package repository;

import java.util.List;

import pojo.CarRental;
import pojo.CarRentalId;

public interface ICarRentalRepository {
	 public CarRental findById(CarRentalId carRentalId);
	 void save(CarRental carRental);
	 public void update(CarRental carRental);
		List<CarRental> findByCustomerAndCarName(String email, String carName);
	    List<CarRental> getAllRentals();
}
