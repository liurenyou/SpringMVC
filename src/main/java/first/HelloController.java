package first;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HelloController implements Controller {
 
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("Hello!");
		// view:一个jsp页面的名字
		String view = "msg";
		// model 需要在页面上显示数据信息
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("message", "Hello!");
		// 添加返回值 ModelAndView 对象
		ModelAndView mv = new ModelAndView(view, model);
		return mv;
	}
}
