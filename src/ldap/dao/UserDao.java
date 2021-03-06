package ldap.dao;

import java.util.List;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import ldap.bean.CSMUser;
import ldap.entity.User;

import org.springframework.stereotype.Repository;


@Repository
public interface UserDao {
	public List<User> getPersonList(User user) ;

	public User queryPerson(String string);
	
	public void save(CSMUser csmUser);

	public List getAllUsers();
}
