package pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Customer")
public class Customer {

   

	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getCustomerID() {
		return customerID;
	}
	public void setReview(Set<Review> review) {
		this.review = review;
	}

	public Customer(Integer customerID, String customerName, String mobile, Date birthday, String identityCard,
			String licenceNumber, Date licenceDate, String email, String password, Account account
			) {
	
		this.customerID = customerID;
		this.customerName = customerName;
		this.mobile = mobile;
		this.birthday = birthday;
		this.identityCard = identityCard;
		this.licenceNumber = licenceNumber;
		this.licenceDate = licenceDate;
		this.email = email;
		this.password = password;
		this.account = account;
		
	}

	@Id
    @Column(name = "CustomerID", length = 30)
	@GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer customerID;

    @Column(name = "CustomerName", length = 50, nullable = false)
    private String customerName;

    @Column(name = "Mobile", length = 15, nullable = false)
    private String mobile;

    @Column(name = "Birthday", nullable = false)
    private Date birthday;

    @Column(name = "IdentityCard", length = 20, nullable = false)
    private String identityCard;

    @Column(name = "LicenceNumber", length = 20, nullable = false)
    private String licenceNumber;

    @Column(name = "LicenceDate", nullable = false)
    private Date licenceDate;

    @Column(name = "Email", length = 50, nullable = false)
    private String email;

    @Column(name = "Password", length = 50, nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "AccountID", referencedColumnName = "AccountID")
    private Account account;
    
	@OneToMany(cascade =CascadeType.ALL)
	@JoinColumn(name="CustomerID")
    private Set<CarRental> carRentals;

	@OneToMany(cascade =CascadeType.ALL)
	@JoinColumn(name="CustomerID")
    private Set<Review> review;

	

	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getLicenceNumber() {
		return licenceNumber;
	}

	public void setLicenceNumber(String licenceNumber) {
		this.licenceNumber = licenceNumber;
	}

	public Date getLicenceDate() {
		return licenceDate;
	}

	public void setLicenceDate(Date licenceDate) {
		this.licenceDate = licenceDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public Set<CarRental> getCarRentals() {
		return carRentals;
	}

	public void setCarRentals(Set<CarRental> carRentals) {
		this.carRentals = carRentals;
	}

	public Set<Review> getReview() {
		return review;
	}
}