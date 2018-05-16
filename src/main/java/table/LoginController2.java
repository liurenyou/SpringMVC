package table;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loginController")
public class LoginController2 {
	@Resource
	private UserService2 service;

	@RequestMapping("/login_page.form")
	public String loginPage() {
		return "login_page";
	}

	@RequestMapping("/login.form")
	public String login(String username, String password, ModelMap model, HttpServletRequest req) {
		try {
			User2 user = service.login(username, password);
			HttpSession session = req.getSession();
			session.setAttribute("loginUser", user);
			model.put("loginUser", user);
			return "redirect:../table/list.form";
		} catch (Exception e) {
			e.printStackTrace();
			model.put("error2",e.getMessage());
			return "login_page";
		}
	}
}
