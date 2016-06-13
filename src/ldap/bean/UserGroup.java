package ldap.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity 
@Table(name = "UserGroup")
public class UserGroup {
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	@Column(name = "id", length = 50)
	private String id;
	
	@Column(name = "groupEncode", length = 50)
	private String groupEncode;
	
	@Column(name = "type", length = 50)
	private String type;
	
	@Column(name = "name", length = 50)
	private String name;
	
	@Column(name = "description", length = 50)
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupEncode() {
		return groupEncode;
	}

	public void setGroupEncode(String groupEncode) {
		this.groupEncode = groupEncode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
