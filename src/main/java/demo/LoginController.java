package demo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import entity.UserInfo;

@Controller
@RequestMapping("/demo")
public class LoginController {
	// 仅是转发功能
	@RequestMapping("/form.form")
	public String form() {
		return "login";
	}

	// 1.1 利用requset对象接收浏览器提交的数据
	@RequestMapping("/login1.form")
	public String login(HttpServletRequest req) throws Exception {
		req.setCharacterEncoding("UTF-8");
		String ua = req.getHeader("User-Agent");
		System.out.println(ua);
		String name = req.getParameter("username");
		String pwd = req.getParameter("pwd");
		System.out.println(name + "," + pwd);
		return "login";
	}

	/*
	 * 1.2 表单参数自动注入： 
	 * 控制器方法参数与表单中输入框name的属性一致
	 */
	@RequestMapping("/login2.form")
	public String login2(String username, @RequestParam("for") String password) {
		System.out.println(username + "," + password);
		return "login";
	}

	/*
	 * 1.3 利用值对象打包封装表单中的数据
	 */
	@RequestMapping("/login3.form")
	public String login3(UserInfo user) {
		System.out.println(user);
		return "login";
	}

	/*
	 * 2.1 利用ModelMap传递数据
	 */
	@RequestMapping("/login4.form")
	// ModelAttribute是为了把从浏览器接收到的用户名再发送到浏览器
	public String login4(@ModelAttribute("username") String username, String password, ModelMap model) {
		System.out.println(username + "," + password);
		model.addAttribute("nameError", "用户名错误");
		model.addAttribute("passwordError", "密码错误");
		return "login";
	}
	
	

}
