package login;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Resource
	private UserDao dao;

	public User login(String name, String pwd) {
		// 从数据库中查询用户信息
		User user = dao.findUserByName(name);
		if(user == null) {
			throw new RuntimeException("登录失败");
		}
		// 登录成功
		if(user.getPassword().equals(pwd)) {
			return user;
		}
		throw new RuntimeException("登录失败");
	}
}
