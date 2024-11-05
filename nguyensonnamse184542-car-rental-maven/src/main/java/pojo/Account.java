package pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Account")
public class Account {

	

	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Id
	@Column(name = "AccountID", length = 30)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer accountID;
	
	@Column(name = "AccountName", length = 30)
	private String accountName;

	@Column(name = "Role")
	private String role;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
    private Customer customer;
	
	public Account(Integer accountID, String accountName, String role) {
		super();
		this.accountID = accountID;
		this.accountName = accountName;
		this.role = role;
	}
	  public Account() {
	        // No-argument constructor required by JPA
	    }
	public Account(Integer accountID, String accountName, String role, Customer customer) {
		super();
		this.accountID = accountID;
		this.accountName = accountName;
		this.role = role;
		this.customer = customer;
	}
	public Integer getAccountID() {
		return accountID;
	}

	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


}