package pojo;

import java.io.Serializable;
import java.util.Objects;

public class ReviewId implements Serializable {
    private Integer customer; // Assuming CustomerID is Long
    private Integer car;      // Assuming CarID is Long

    public ReviewId() {
    }

    public ReviewId(Integer customer, Integer car) {
        this.customer = customer;
        this.car = car;
    }

    // Getters and Setters
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
        if (!(o instanceof ReviewId)) return false;
        ReviewId that = (ReviewId) o;
        return Objects.equals(customer, that.customer) && Objects.equals(car, that.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, car);
    }
}
