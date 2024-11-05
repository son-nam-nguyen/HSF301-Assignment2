package service;

import java.util.List;

import pojo.CarProducer;

public interface ICarProducerService {
	public CarProducer findByProducerName(String producerName);
	public List<CarProducer> getAllCarProducer();
}
