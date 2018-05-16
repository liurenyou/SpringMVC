package table;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AccessInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception ex)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView model)
			throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		HttpSession session = req.getSession();
		User2 user = (User2) session.getAttribute("loginUser");
		// 若session中的user不存在了则重定向回登录页面
		if(user == null) {
			String path = "../loginController/login_page.form";
			res.sendRedirect(path);
			return false;
		}
		return true;
	}

}
