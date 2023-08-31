package smart.poc.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import smart.poc.mapping.UserMapper;
import smart.poc.pojo.User;

public class UserDAOImpl extends SqlSessionDaoSupport implements UserDAO {
	@Autowired
	private UserMapper userMapper;

	@Override
	public User findUserById(int id) {
		// TODO Auto-generated method stub

		User user = userMapper.selectById(1);
		System.out.print("In DAO IMP :" + user);
		return user;
	}

	@Override
	public void insert(User user) {
		// TODO Auto-generated method stub
		userMapper.insert(user);
	}

}
