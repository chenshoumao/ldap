package ldap.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity 
@Table(name = "ldap_VIP")
public class VIP extends User{
	 @Column(name = "fullname", length=50)
	 private String fullname;
	 
	 @Column(name = "photo", length=50)
	 private String photo;
	 
	 @Column(name = "idCard", length=50)
	 private String idCard;
	 
	 @Column(name = "address", length=50)
	 private String address;

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
	 
	 
}
