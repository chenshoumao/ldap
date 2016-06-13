package ldap.control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ldap")
public class Ldap {
	@RequestMapping("/shows.action")
	@ResponseBody
	public Map<String, Object> shows(){
		System.out.println(123);
		System.out.println(123);
		System.out.println(123);
		Map<String, Object> map = new HashMap<>();
		map.put("one", "one");
		return map;
	}
}
