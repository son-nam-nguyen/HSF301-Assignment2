package service;

import java.util.List;
import dao.CarDAO;
import pojo.Car;

public class CarService implements ICarService {
    private CarDAO carDAO;

    public CarService(String persistenceName) {
        this.carDAO = new CarDAO(persistenceName);
    }

    @Override
    public void save(Car car) {
        carDAO.save(car);
    }

    @Override
    public void delete(Integer carId) {
        carDAO.delete(carId);
    }

    @Override
    public Car findById(Integer carId) {
        return carDAO.findById(carId);
    }

    @Override
    public void update(Car car) {
        carDAO.update(car);
    }

    @Override
    public List<Car> getAllCars() {
        return carDAO.getAllCars();
    }
    @Override
	  public Car findByCarName(String carName) {
		return carDAO.findByCarName(carName);  
	  }
	  

}
