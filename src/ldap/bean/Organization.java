package ldap.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity 
@Table(name = "Organization")
public class Organization extends Tree{
	
	
	  @Column(name = "organizeEncode", length=50)
	  private String organizeEncode;
	  
	
	  
	  @Column(name = "organizationShort", length=50)
	  private String organizationShort;
	  
	  @Column(name = "description", length=50)
	  private String description;
	  
	  @Column(name = "type", length=50)
	  private String type;
	  
	  @Column(name = "state", length=50)
	  private String state;
	  
	  @Column(name = "superiorEncode", length=50)
	  private String superiorEncode;
	  
	  @Column(name = "organizationLevel", length=50)
	  private String organizationLevel;
	  
	  @Column(name = "displayOrder", length=50)
	  private String displayOrder;
	  
	  @Column(name = "address", length=50)
	  private String address;
	  
	  @Column(name = "postalCode", length=50)
	  private String postalCode;
	  
	  @Column(name = "mobile", length=50)
	  private String mobile;
	  
	  @Column(name = "homeFax", length=50)
	  private String homeFax;

	public String getOrganizeEncode() {
		return organizeEncode;
	}

	public void setOrganizeEncode(String organizeEncode) {
		this.organizeEncode = organizeEncode;
	}

 
	public String getOrganizationShort() {
		return organizationShort;
	}

	public void setOrganizationShort(String organizationShort) {
		this.organizationShort = organizationShort;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSuperiorEncode() {
		return superiorEncode;
	}

	public void setSuperiorEncode(String superiorEncode) {
		this.superiorEncode = superiorEncode;
	}

	public String getOrganizationLevel() {
		return organizationLevel;
	}

	public void setOrganizationLevel(String organizationLevel) {
		this.organizationLevel = organizationLevel;
	}

	public String getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getHomeFax() {
		return homeFax;
	}

	public void setHomeFax(String homeFax) {
		this.homeFax = homeFax;
	}

 
	  
	  

 
	  
}
