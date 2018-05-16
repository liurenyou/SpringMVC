package table;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/table")
public class UserController2 {
	@Resource
	private UserService2 service;
	@RequestMapping("/list.form")
	public String list(ModelMap model) {
		// 调用业务层
		List<User2> list = service.list();
		// 将用户列表发送到jsp
		model.addAttribute("users",list);
		System.out.println(list);
		// 转发到 list-user页面
		return "list_user";
	}
	
	@RequestMapping("/map.form")
	public String map(String name, ModelMap map) {
		List<Map<String, Object>> list = service.list2(name);
		map.put("users", list);
		return "list_map";
	}
}
