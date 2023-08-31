package smart.poc.dao;

import java.util.List;

import smart.poc.pojo.Usr;

public interface UsrDAO {

	// 根据 id 查询用户信息
	public Usr findUserById(int id);

	public List<Usr> getAllusr();

	public void insert(Usr usr);

	public int totalUsrs();

	public List<Usr> listUsrsByPage(int currPage, int pageSize);

	public void deleteUserById(int id);

}