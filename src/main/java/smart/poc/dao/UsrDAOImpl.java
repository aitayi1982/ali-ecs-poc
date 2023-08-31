package smart.poc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import smart.poc.mapping.UsrMapper;
import smart.poc.pojo.Usr;

public class UsrDAOImpl extends SqlSessionDaoSupport implements UsrDAO {
	@Autowired
	private UsrMapper usrMapper;

	@Override
	public Usr findUserById(int id) {
		// TODO Auto-generated method stub
		//System.out.println("ID : " + id);
		Usr usr = usrMapper.selectById(id);
		//System.out.print("In DAO IMP :" + usr);
		//System.out.println(usr.getBirthday());
		return usr;
	}

	@Override
	public List<Usr> getAllusr() {
		List<Usr> usrs = this.usrMapper.queryAll();
		// TODO Auto-generated method stub
		// System.out.println("ID : " + id);
		// #Usr usr = usrMapper.selectById(id);
		// System.out.print("In DAO IMP :" + usr);
		// System.out.println(usr.getBirthday());

		return usrs;
	}

	@Override
	public void insert(Usr usr) {
		// TODO Auto-generated method stub
		usrMapper.insert(usr);
	}
	

	@Override
	public int totalUsrs() {
		// TODO Auto-generated method stub
		return usrMapper.totalUsrs();
	}

	@Override
	public List<Usr> listUsrsByPage(int currPage, int pageSize) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("currPage", currPage);
		data.put("pageSize", pageSize);
		return this.usrMapper.listUsrsByPage(data);
	}

	@Override
	public void deleteUserById(int id) {
		// TODO Auto-generated method stub
		this.usrMapper.deleteUserById(id);
		
	}

}
