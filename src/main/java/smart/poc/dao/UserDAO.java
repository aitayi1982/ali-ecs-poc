package smart.poc.dao;

import smart.poc.pojo.User;

public interface UserDAO {

	// 根据 id 查询用户信息
	public User findUserById(int id);

	public void insert(User user);
}