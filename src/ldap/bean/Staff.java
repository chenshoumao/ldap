package ldap.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity 
@Table(name = "ldap_staff")
public class Staff extends User{
	@Column(name = "fullname", length=50)
	 private String fullname;
	
	@Column(name = "photo", length=50)
	 private String photo;
	
 
	
	@Column(name = "idCard", length=50)
	 private String idCard;
	
	@Column(name = "address", length=50)
	 private String address;
	
	@Column(name = "staffNumber", length=50)
	 private String staffNumber;
	
	@Column(name = "type", length=50)
	 private String type;
	
	@Column(name = "duty", length=50)
	 private String duty;
	
	@Column(name = "rank", length=50)
	 private String rank;

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	 

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStaffNumber() {
		return staffNumber;
	}

	public void setStaffNumber(String staffNumber) {
		this.staffNumber = staffNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
	
	
}
