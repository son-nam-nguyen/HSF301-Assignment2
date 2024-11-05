package pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CarProducer")
public class CarProducer {

    public CarProducer() {
	}

	@Id
    @Column(name = "ProducerID", length = 30)
	@GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer producerID;

    @Column(name = "ProducerName", length = 50, nullable = false)
    private String producerName;

    @Column(name = "Address", length = 100, nullable = false)
    private String address;

    @Column(name = "Country", length = 50, nullable = false)
    private String country;
    
    @OneToMany(mappedBy = "producerID", cascade = CascadeType.ALL)
    private Set<Car> cars;

	public CarProducer(Integer producerID, String producerName, String address, String country) {
		super();
		this.producerID = producerID;
		this.producerName = producerName;
		this.address = address;
		this.country = country;
	}
	
	

	public CarProducer(String producerName, String address, String country) {
		super();
		this.producerName = producerName;
		this.address = address;
		this.country = country;
	}



	public Integer getProducerID() {
		return producerID;
	}

	public void setProducerID(Integer producerID) {
		this.producerID = producerID;
	}

	public String getProducerName() {
		return producerName;
	}

	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

    
    
}
