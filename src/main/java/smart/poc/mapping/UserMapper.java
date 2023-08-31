package smart.poc.mapping;

import smart.poc.pojo.User;

public interface UserMapper {

	void insert(User user);

	User selectById(Integer id);

}