package pojo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Review")
@IdClass(ReviewId.class) // Use the composite key class
public class Review implements Serializable {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID", nullable = false)
    private Customer customer;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CarID", referencedColumnName = "CarID", nullable = false)
    private Car car;

    @Column(name = "ReviewStar", nullable = false)
    private int reviewStar;

    @Column(name = "Comment", length = 255, nullable = false)
    private String comment;

    public Review() {
    }

    public Review(Customer customer, Car car, int reviewStar, String comment) {
        this.customer = customer;
        this.car = car;
        this.reviewStar = reviewStar;
        this.comment = comment;
    }

    // Getters and Setters
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

    public int getReviewStar() {
        return reviewStar;
    }

    public void setReviewStar(int reviewStar) {
        this.reviewStar = reviewStar;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
