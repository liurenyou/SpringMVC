package test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import login.User;
import login.UserDao;
import login.UserService;
import note.HelloController2;
import table.User2;
import table.UserDao2;
import table.UserService2;

public class TestCase {
	ClassPathXmlApplicationContext ctx;

	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext("spring-mvc2.xml");
	}

	// 1.测试注解版本控制器
	@Test
	public void testController2() {
		HelloController2 bean = ctx.getBean("helloController2", HelloController2.class);
		System.out.println(bean);
	}

	// 2.测试findUserByName
	@Test
	public void testFindUserByName() {
		UserDao dao = ctx.getBean("userDao", UserDao.class);
		User user = dao.findUserByName("tom");
		System.out.println(user);
	}

	// 3.测试登录
	@Test
	public void testLogin() {
		UserService service = ctx.getBean("userService", UserService.class);
		User user = service.login("tom", "123");
		System.out.println(user);
		user = service.login("tom", "1234");
	}

	// 4.测试findAllUser
	@Test
	public void testFindAllUser() {
		UserDao2 dao = ctx.getBean("userDao2", UserDao2.class);
		List<User2> list = dao.findAllUser();
		for (User2 user : list) {
			System.out.println(user);
		}
	}

	// 5.测试业务层方法
	@Test
	public void testUserService() {
		UserService2 service = ctx.getBean("userService2", UserService2.class);
		List<User2> list = service.list();
		for (User2 user : list) {
			System.out.println(user);
		}
	}

	// 6.测试JdbcTemplate的插入数据
	@Test
	public void testJdbcTemplate() {
		JdbcTemplate template = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
		String sql = "insert into t_user (id,username,password,mobile,address) value (?,?,?,?,?)";
		// 利用JdbcTemplate执行插入语句
		template.update(sql, 4, "liurenyou", "123", "1234567", "地址");
		// update方法的底层封装了JDBC操作
	}

	// 7.测试JdbcTemplate的更新数据
	@Test
	public void testUpdate() {
		JdbcTemplate template = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
		String sql = "update t_user set address=? where id=?";
		int n = template.update(sql, "大理", 4);
		System.out.println(n);
	}

	// 8.测试JdbcTemplate的删除数据
	@Test
	public void testDelete() {
		JdbcTemplate template = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
		String sql = "delete from t_user where id=?";
		int n = template.update(sql, 4);
		System.out.println(n);
	}

	/*
	 *  9.查询数据
	 *  
	 *  9.1 返回多个结果的查询方法：
	 *  方法一：List jdbcTemplate.query(sql,RowMapper)
	 *  方法二：List jdbcTemplate.query(sql,Parameter,RowMapper)
	 *  
	 *  9.2 返回一个结果的参数方法：
	 *  Object jdbcTemplate.queryForObject(sql,Parameter,RowMapper)
	 */
	@Test
	public void testQuery() {
		JdbcTemplate template = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
		String sql = "select * from t_user";
		// 封装结果集的一行，映射为一个User对象
		RowMapper<User2> rsToUser2 = new RowMapper<User2>() {
			@Override
			public User2 mapRow(ResultSet rs, int index) throws SQLException {
				// 将当前rs中的一行数据映射到User对象中
				User2 user = new User2();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setMobile(rs.getString("mobile"));
				user.setAddress(rs.getString("address"));
				return user;
			}
		};
		// 1.查询多个结果
		List<User2> list = template.query(sql, rsToUser2);
		for(User2 user : list) {
			System.out.println("查询多结果：" + user);
		}
		/*
		 * 当RowMapper被重用以后，代码就会大大简化，执行登录SQL查询，
		 * 将重复使用RowMapper
		 */
		sql = "select * from t_user where username=?";
		// 查询参数
		String[] param = {"Tom"};
		// 2.查询一个结果
		User2 user = template.queryForObject(sql, param, rsToUser2);
		System.out.println("查询一个结果：" + user);
		// 3.多参数查询
		sql = "select * from t_user where username=? and password=?";
		Object[] param2 = {"Tom", "123"};
		List<User2> users = template.query(sql, param2,rsToUser2);
		for(User2 u : users) {
			System.out.println("多参数查询：" + u);
		}
		// 4.多参数like查询
		sql = "select * from t_user where username like ? or mobile like ?";
		param2 = new Object[]{"%o%", "%1%"};
		users = template.query(sql, param2, rsToUser2);
		for(User2 u : users) {
			System.out.println("多参数like查询：" + u);
		}
	}
	
	/*
	 * 10.测试Map封装结果
	 */
	@Test
	public void testFindUsers() {
		UserDao2 dao = ctx.getBean("userDao2",UserDao2.class);
		List<Map<String, Object>> list = dao.findUsers();
		for(Map<String, Object> map : list) {
			System.out.println(map);
		}
	}
	@Test
	public void testFindUserLikeName() {
		UserDao2 dao = ctx.getBean("userDao2",UserDao2.class);
		List<Map<String, Object>> list = dao.findUserLikeName("o");
		for(Map<String, Object> map : list) {
			System.out.println(map);
		}
	}
}
