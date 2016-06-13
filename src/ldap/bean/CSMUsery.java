package ldap.bean;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CSMUseryWW")
public class CSMUsery {

	
	   @Id
	   @GenericGenerator(name="uuid", strategy="uuid")
	   @GeneratedValue(generator="uuid")
	   @Column(name="id", length=50)
	   private String id;
	  
	
	@Column(name = "address", length=50)
	private String address;
	 
	
	@Column(name = "description", length=55)
	private String description;


	public String getId() {
		return id;
	}


	public void setId_project(String id) {
		this.id = id;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	 
	
	 
	
}
