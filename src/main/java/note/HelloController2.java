package note;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/note")
public class HelloController2 {
	@RequestMapping("/hello.form")
	public String excute() {
		System.out.println("hello!");
		// 此处是返回给jsp的名称
		return "msg2";
	}

	/*
	 *  1.利用ModeAndView传输数据
	 *  	松耦合，控制器与Servlet API无关，可以脱离Web容器单独测试
	 *  	方便实现平台无关的测试
	 */
	@RequestMapping("/test.form")
	public ModelAndView test() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("message", "Hello!");
		model.put("count", 300);
		String[] names = { "甲", "乙", "丙" };
		model.put("names", names);

		// 视图名称
		String view = "msg3";
		System.out.println("test");
		return new ModelAndView(view, model);
	}
	
	/*
	 *  2.利用HttpServletRequest对象的setAttribute方法传输数据
	 *  	控制器方法紧紧绑定了Servlet API，只能在Web容器中进行测试
	 *  	但是可以调用Servlet底层的API
	 */
	@RequestMapping("/test2.form")
	public String demo(HttpServletRequest req) {
		req.setAttribute("msg", "Hi!");
		// 返回用户端的地址 getRemoteAddr()
		req.setAttribute("ip", req.getRemoteAddr());
		System.out.println("demo");
		return "msg4";
	}
}
