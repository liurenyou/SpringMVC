package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class DemoInterceptor implements HandlerInterceptor {

	// 视图处理器之后、jsp之前执行的方法
	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion");
	}
	
	// 控制器之后、视图处理器之前执行的方法
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView model)
			throws Exception {
		System.out.println("postHandle");
	}

	// 控制之前执行的方法，返回true，控制器继续执行，返回false则会拦截
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		System.out.println("preHandle");
		return true;
	}

}
