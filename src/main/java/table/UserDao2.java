package table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 数据访问 -- 持久层
 * 
 * @author liurenyou
 *
 */
@Repository
public class UserDao2 {
	// 管理数据库连接
	@Resource
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<User2> rowMapper = new RowMapper<User2>() {
		
		@Override
		public User2 mapRow(ResultSet rs, int RowNum) throws SQLException {
			User2 user = new User2();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setMobile(rs.getString("mobile"));
			user.setAddress(rs.getString("address"));
			return user;
		}
	};
	
	// 查询全部的用户信息
	public List<User2> findAllUser() {
		String sql = "select * from t_user";
		// 访问数据库
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			List<User2> list = new ArrayList<User2>();
			while (rs.next()) {
				// 处理查询结果
				User2 user = new User2();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setMobile(rs.getString("mobile"));
				user.setAddress(rs.getString("address"));
				list.add(user);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public User2 findUserByName(String username) {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select * from t_user where username=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			User2 user = null;
			while (rs.next()) {
				user = new User2();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setMobile(rs.getString("mobile"));
				user.setAddress(rs.getString("address"));
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * 利用Map封装查询结果
	 * 为了避免查询表中的部分列而出现大量冗杂数据，springJDBC提供了Map封装查询结果
	 * 可以按需获取少部分数据列，减少数据量提高性能
	 */
	public List<Map<String, Object>> findUsers() {
		String sql = "select id as \"id\",username as \"username\" from t_user";
		return jdbcTemplate.queryForList(sql);
	}
	
	public List<Map<String, Object>> findUserLikeName(String name) {
		String sql = "select id as \"id\",username as \"username\" from t_user"
				+ " where username like ?";
		return jdbcTemplate.queryForList(sql, new Object[]{"%"+name+"%"});
		
	}
}
