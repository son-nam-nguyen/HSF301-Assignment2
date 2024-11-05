package repository;

import java.util.List;
import pojo.Car;

public interface ICarRepository {
    void save(Car car);                    
    void delete(Integer carId);             
    Car findById(Integer carId);            
    void update(Car car);                   
    List<Car> getAllCars();                 
	  public Car findByCarName(String carName);

}