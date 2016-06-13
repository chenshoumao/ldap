package ldap.dao.impl;


import static org.springframework.ldap.query.LdapQueryBuilder.query;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.naming.Name;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.ldap.PagedResultsResponseControl;

import ldap.bean.CSMUser;
import ldap.dao.HibernateDao;
import ldap.dao.IDao;
import ldap.dao.UserDao;
import ldap.entity.PersonAttributeMapper;
import ldap.entity.User;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.control.PagedResultsCookie;
import org.springframework.ldap.control.PagedResultsDirContextProcessor;
import org.springframework.ldap.control.PagedResultsRequestControl;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.AttributesMapperCallbackHandler;
import org.springframework.ldap.core.CollectingNameClassPairCallbackHandler;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.core.support.LdapOperationsCallback;
import org.springframework.ldap.core.support.SingleContextSource;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

 

 

import antlr.StringUtils;
 
 

@Repository
public class UserDaoImpl implements UserDao{
	@Resource
    private LdapTemplate ldapTemplate; 
	
	@Autowired(required = true)
	@Qualifier(value = "contextSource")
	private ContextSource contextSource;
	
	 @Resource(type=HibernateDao.class)
	 private IDao dao;
	 
	 @Resource 
	 private HibernateDao hdao;
	
	public List<User> getPersonList(User user) { 
        List<User> list = new ArrayList<User>();  
        //查询过滤条件  
        AndFilter andFilter = new AndFilter();  
        andFilter.and(new EqualsFilter("objectclass", "person"));  
          
        if (user.getCn() != null  
                && user.getCn().length() > 0) {  
            andFilter.and(new EqualsFilter("cn", user.getCn()));  
        }  
        if (user.getSn() != null  
                && user.getSn().length() > 0) {  
            andFilter.and(new EqualsFilter("sn", user.getSn()));  
        }  
  
        if (user.getDescription() != null  
                && user.getDescription().length() > 0) {  
            andFilter.and(new EqualsFilter("description", user.getDescription()));  
        }  
   
        
        if (user.getAddress() != null   
                && user.getAddress().length() > 0) {  
            andFilter.and(new EqualsFilter("mobile", user.getAddress()));  
        }  
        //search是根据过滤条件进行查询，第一个参数是父节点的dn，可以为空，不为空时查询效率更高  
        list = ldapTemplate.search("", andFilter.encode(),  
                new PersonAttributeMapper());  
        
        return list;  
    }
	
	public List<User> getAllUsers() {
		SearchControls controls = new SearchControls();
		controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
	      return ldapTemplate.search(query().where("objectclass").is("top"),  new PersonAttributeMapper());
	}
	//有bug
	public List<User> findAllUsers(final int page, int rows)
    {

        final SearchControls searchControls = new SearchControls();
		  searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

		  final PagedResultsDirContextProcessor processor =
		        new PagedResultsDirContextProcessor(rows);

		  return SingleContextSource.doWithSingleContext(    contextSource, new LdapOperationsCallback<List<User>>() {

		      @Override
		      public List<User> doWithLdapOperations(LdapOperations operations) {
			   Map<String, Object> map = new HashMap<String, Object>();
		        List<User> result = new LinkedList<User>();
                int i=1;
		        do {
		          List<User> oneResult = operations.search("cn=users", "(&(objectclass=person))", searchControls, new PersonAttributeMapper() , processor);
		          map.put(""+i,oneResult);
		          i++;
		        } while(processor.hasMore());
		        result=(List<User>) map.get(""+ page);
		        return result ;
		      }
		  });
		 
       
    }  
	
	
	public boolean addUser(User vo) {
	    try {
	        // 基类设置
		BasicAttribute ocattr = new BasicAttribute("objectClass");
		ocattr.add("top");
		ocattr.add("person");
//		ocattr.add("uidObject");
		ocattr.add("inetOrgPerson");
		ocattr.add("organizationalPerson");
		// 用户属性
		Attributes attrs = new BasicAttributes();
		attrs.put(ocattr);
		attrs.put("cn",vo.getCn());
		attrs.put("sn",vo.getSn());
//		attrs.put("displayName", StringUtils.trimToEmpty(vo.getRealname()));
//		attrs.put("mail", StringUtils.trimToEmpty(vo.getEmail())); 
		attrs.put("description",vo.getDescription());
	 
		ldapTemplate.bind("uid=" + vo.getCn().trim(), null, attrs);
		return true;
	    } catch (Exception ex) {
		ex.printStackTrace();
		return false;
	    }
	}
	
	public boolean updateUser(CSMUser vo) {
	    try {
		ldapTemplate.modifyAttributes("uid=" + vo.getUid().trim(), new ModificationItem[] {
		    new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("cn", vo.getCn().trim())),
		 
		    new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("sn", vo.getSn().trim())),
		    new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("description", vo.getDescription().trim())),
 
		    new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("address", vo.getAddress().trim()))
		});
		return true;
	    } catch (Exception ex) {
		ex.printStackTrace();
		return false;
	    }
	}
	
	public boolean deleteUser(String username) {
	    try {
		ldapTemplate.unbind("uid=" + username.trim());
		return true;
	    } catch (Exception ex) {
		ex.printStackTrace();
		return false;
	    }
	}

	

	  public User getPersonDetail(String cn) {  
	        User ua = (User)   
			    ldapTemplate.lookup( cn,  
			            new PersonAttributeMapper());  
			return ua;  
	    }  

	  //获取用户uid ；如 uid=test1
	  public Name getPersonDn(String userId) throws Exception {
	        AndFilter andFilter = new AndFilter();
	        andFilter.and(new EqualsFilter("uid", userId));
			List<Name> result = this.ldapTemplate.search("", andFilter.encode(),
	                SearchControls.SUBTREE_SCOPE, new AbstractContextMapper() {
	                    @Override
	                    protected Name doMapFromContext(DirContextOperations adapter) {
	                        return adapter.getDn();
	                    }
	                });
	        if (null == result || result.size() != 1) {
	            throw new Exception();
	        } else {
	        	Name str = result.get(0);
	            return result.get(0);
	        }

	    }

	
	@Override
	public User queryPerson(String userId) {
		 try { 
			return (User) ldapTemplate.lookup(getPersonDn(userId),new PersonAttributeMapper());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public void save(CSMUser csmUser) {
		// TODO Auto-generated method stub
		this.dao.save(csmUser);
	}

	public void find(String str) {
		// TODO Auto-generated method stub
		List list = this.hdao.find(str);
		System.out.println(list.size());
	}

 
	 
}
