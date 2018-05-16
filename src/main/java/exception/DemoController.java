package exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exception")
public class DemoController {
	/*
	 * 1.简单异常处理步骤： 
	 * 1.1 添加异常处理界面 
	 * 1.2 添加异常处理配置(配置异常类型与错误页面对应关系) 
	 * 1.3 在控制器中抛出异常的时候转发显示错误页面
	 */
	@RequestMapping("test.form")
	public String excute(String name) {
		if ("tom".equals(name)) {
			int[] ary = { 1, 2, 3 };
			int i = ary[-1];
		}
		return "success";
	}

	/*
	 * 2.使用注解@ExceptionHandler处理异常
	 */
	@ExceptionHandler
	public String exec(HttpServletRequest req, Exception ex) {
		System.out.println("发现异常！");
		ex.printStackTrace();
		if(ex instanceof NullPointerException) {
			return "error2";
		}
		return "error1";
	}
	@RequestMapping("/test2.form")
	public String test2(String name) {
		if ("jerry".equals(name)) {
			String s = null;
			s.charAt(0);
		}
		return "success";
	}

}
