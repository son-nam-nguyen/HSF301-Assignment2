package pojo;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CarRentalId implements Serializable {
    private Integer customer;
    private Integer car;

    public CarRentalId() {
    }

    public CarRentalId(Integer customer, Integer car) {
        this.customer = customer;
        this.car = car;
    }

    public Integer getCustomer() {
        return customer;
    }

    public void setCustomer(Integer customer) {
        this.customer = customer;
    }

    public Integer getCar() {
        return car;
    }

    public void setCar(Integer car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarRentalId)) return false;
        CarRentalId that = (CarRentalId) o;
        return Objects.equals(customer, that.customer) && Objects.equals(car, that.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, car);
    }
}
