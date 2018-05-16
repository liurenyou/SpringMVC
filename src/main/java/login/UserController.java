package login;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class UserController {
	@Resource
	private UserService userService;

	// 跳转到登录页面
	@RequestMapping("/loginpage.form")
	public String loginpage() {
		return "login-page";
	}

	// 检查登录
	@RequestMapping("/check.form")
	public String login(String username, String password, ModelMap model) {
		try {
			User user = userService.login(username, password);
			model.addAttribute("msg", "欢迎" + user.getUsername() + "登录");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			// 登录失败
			model.addAttribute("error", e.getMessage());
			return "login-page";
		}
	}
}
