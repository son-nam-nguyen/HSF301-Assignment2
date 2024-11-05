package service;

import java.util.List;


import pojo.CarProducer;
import repository.IProducerRepository;
import repository.ProducerRepository;

public class CarProducerService implements ICarProducerService {
private IProducerRepository carProducerRepo;
	
	public CarProducerService(String jpaName) {
		
		carProducerRepo = new ProducerRepository(jpaName);
	}
	@Override
	public CarProducer findByProducerName(String producerName) {
		return carProducerRepo.findByProducerName(producerName);
	}

	@Override
	public List<CarProducer> getAllCarProducer() {
		return carProducerRepo.getAllCarProducer();
	}

}
