package pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import enums.CarStatus;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Car")
public class Car {

    

	@Id
    @Column(name = "CarID", length = 30)
	@GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer carID;

    @Column(name = "CarName", length = 50, nullable = false)
    private String carName;

    @Column(name = "CarModelYear", nullable = false)
    private int carModelYear;

    @Column(name = "Color", length = 30, nullable = false)
    private String color;

    @Column(name = "Capacity", nullable = false)
    private int capacity;

    @Column(name = "Description", length = 255, nullable = false)
    private String description;

    @Column(name = "ImportDate", nullable = false)
    private Date importDate;

    @ManyToOne
    @JoinColumn(name = "ProducerID", nullable = false)
    private CarProducer producerID;

    @Column(name = "RentPrice", nullable = false)
    private double rentPrice;

    @Column(name = "Status", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private CarStatus status;
    
	@OneToMany(cascade =CascadeType.ALL)
	@JoinColumn(name="CustomerID")
    private Set<CarRental> carRentals;

	@OneToMany(cascade =CascadeType.ALL)
	@JoinColumn(name="CarID")
    private Set<Review> review;

	public Car(Integer carID, String carName, int carModelYear, String color, int capacity, String description,
			Date importDate, CarProducer producerID, double rentPrice, CarStatus status) {
		super();
		this.carID = carID;
		this.carName = carName;
		this.carModelYear = carModelYear;
		this.color = color;
		this.capacity = capacity;
		this.description = description;
		this.importDate = importDate;
		this.producerID = producerID;
		this.rentPrice = rentPrice;
		this.status = status;
	}
	public Car() {
	}
	
	
	
	public Car(String carName, int carModelYear, String color, int capacity, String description, Date importDate,
			CarProducer producerID, double rentPrice, CarStatus status) {
		super();
		this.carName = carName;
		this.carModelYear = carModelYear;
		this.color = color;
		this.capacity = capacity;
		this.description = description;
		this.importDate = importDate;
		this.producerID = producerID;
		this.rentPrice = rentPrice;
		this.status = status;
	}



	public Integer getCarID() {
		return carID;
	}
	
	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public int getCarModelYear() {
		return carModelYear;
	}

	public void setCarModelYear(int carModelYear) {
		this.carModelYear = carModelYear;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getImportDate() {
		return importDate;
	}

	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}

	public CarProducer getProducerID() {
		return producerID;
	}
    public String getProducerName() {
        return producerID != null ? producerID.getProducerName() : null; 
    }


	public void setProducerID(CarProducer producerID) {
		this.producerID = producerID;
	}

	public double getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(double rentPrice) {
		this.rentPrice = rentPrice;
	}

	public CarStatus getStatus() {
		return status;
	}

	public void setStatus(CarStatus status) {
		this.status = status;
	}

}
