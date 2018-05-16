package table;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 实现 -- 业务层
 * @author liurenyou
 *
 */
@Service
public class UserService2 {
	@Resource
	private UserDao2 dao;
	// 查询全部的用户信息 @return 用户信息列表
	public List<User2> list() {
		List<User2> list = dao.findAllUser();
		return list;
	}
	
	public User2 login(String name, String pwd) {
		// 从数据库查询用户信息
		User2 user = dao.findUserByName(name);
		if(user == null) {
			throw new RuntimeException("登录失败");
		}
		if(user.getPassword().equals(pwd)) {
			return user;
		}
		throw new RuntimeException("登录失败");
	}
	
	public List<Map<String, Object>> list2(String name) {
		if(name == null || name.trim().isEmpty()) {
			return dao.findUsers();
		}
		return dao.findUserLikeName(name);
	}
}
