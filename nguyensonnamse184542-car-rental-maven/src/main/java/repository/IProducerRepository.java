package repository;

import java.util.List;

import pojo.CarProducer;

public interface IProducerRepository {
	public CarProducer findByProducerName(String producerName);
	public List<CarProducer> getAllCarProducer();
}
