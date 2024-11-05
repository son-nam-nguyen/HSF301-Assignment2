package repository;

import java.util.List;

import dao.CarProducerDAO;
import pojo.CarProducer;

public class ProducerRepository implements IProducerRepository {
	private CarProducerDAO carProducerDAO = null;
	public ProducerRepository(String fileConfig) {
		carProducerDAO = new CarProducerDAO(fileConfig);
	}
	@Override
	public CarProducer findByProducerName(String producerName) {
		// TODO Auto-generated method stub
		return carProducerDAO.findByProducerName(producerName);
	}

	@Override
	public List<CarProducer> getAllCarProducer() {
		// TODO Auto-generated method stub
		return carProducerDAO.getAllCarProducer();
	}
	
	
}
