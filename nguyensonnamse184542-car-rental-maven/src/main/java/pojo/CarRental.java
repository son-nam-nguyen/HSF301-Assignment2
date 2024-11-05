package pojo;

import javax.persistence.*;
import enums.RentalStatus;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "CarRental")
	@IdClass(CarRentalId.class) 
	public class CarRental implements Serializable {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID", nullable = false)
    private Customer customer;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CarID", referencedColumnName = "CarID", nullable = false)
    private Car car;

    @Column(name = "PickupDate", nullable = false)
    private LocalDate pickupDate;

    @Column(name = "ReturnDate", nullable = false)
    private LocalDate returnDate;

    @Column(name = "RentPrice", nullable = false)
    private double rentPrice;

    @Column(name = "Status", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private RentalStatus status;

    public CarRental() {
    }


 
    public CarRental(Customer customer, Car car, LocalDate pickupDate, LocalDate returnDate, double rentPrice, RentalStatus status) {
        this.customer = customer;
        this.car = car;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.rentPrice = rentPrice;
        this.status = status;
    }


    
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDate pickupDate) {
        this.pickupDate = pickupDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public void setStatus(RentalStatus status) {
        this.status = status;
    }

    
    public String getCarName() {
        return car != null ? car.getCarName() : null; 
    }

    public String getCustomerName() {
        return customer != null ? customer.getCustomerName() : null; 
    }
    
    public Integer getCarID() {
        return car != null ? car.getCarID() : null; 
    }

    public Integer getCustomerID() {
        return customer != null ? customer.getCustomerID() : null; 
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarRental)) return false;
        CarRental that = (CarRental) o;
        return Double.compare(that.rentPrice, rentPrice) == 0 &&
                Objects.equals(customer, that.customer) &&
                Objects.equals(car, that.car) &&
                Objects.equals(pickupDate, that.pickupDate) &&
                Objects.equals(returnDate, that.returnDate) &&
                status == that.status;
    }

    
    @Override
    public int hashCode() {
        return Objects.hash(customer, car, pickupDate, rentPrice, returnDate, status);
    }
}
