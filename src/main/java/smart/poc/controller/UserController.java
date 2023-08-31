package smart.poc.controller;

//import java.util.Iterator;
import java.util.List;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import smart.poc.dao.UsrDAO;
import smart.poc.pojo.PageInfo;
import smart.poc.pojo.Usr;

@Controller
public class UserController {

	@Autowired
	private UsrDAO usrDAO;

	@RequestMapping("/users/{currPage}/{pageSize}")
	public String listUsrs(Model model, @PathVariable("currPage") int currPage,
			@PathVariable("pageSize") int pageSize) {

		List<Usr> usrs = this.usrDAO.listUsrsByPage(currPage, pageSize);
		model.addAttribute("name", usrs);
		return "users";
	}

	@RequestMapping("/get/{currPage}/{pageSize}")
	public String getUsrs(Model model, @ModelAttribute PageInfo pageinfo, @PathVariable("currPage") int currPage,
			@PathVariable("pageSize") int pageSize) {
		if (currPage <= 0) {
			currPage = 1;
		}
		if (pageSize <= 0) {
			pageSize = 10;
		}
		System.out.println(" Current Page : " + currPage);
		System.out.println(" pageSize : " + pageSize);

		if (pageinfo == null) {
			pageinfo = new PageInfo();
			pageinfo.setCurrPage(1);
			pageinfo.setFirstPage(1);
			pageinfo.setPageSize(5);

		}
		if (pageinfo.getPageSize() == 0) {
			pageinfo.setCurrPage(1);

			pageinfo.setPageSize(5);
		}
		pageinfo.setNextPage(pageinfo.getCurrPage() + 1);
		pageinfo.setPrePage(pageinfo.getCurrPage() - 1);
		System.out.println("Current Page :" + pageinfo.getCurrPage());
		if (pageinfo.getCurrPage() <= 1) {
			pageinfo.setDisablePreLink(true);
			System.out.println("Not Pre pages.");
		}

		List<Usr> users = this.usrDAO.listUsrsByPage(pageinfo.getCurrPage(), pageinfo.getPageSize());
		pageinfo.setTotolPages(this.usrDAO.totalUsrs() / pageinfo.getPageSize() + 1);

		if (pageinfo.getTotolPages() <= pageinfo.getCurrPage()) {
			pageinfo.setDisableNextLink(true);
			System.out.println("No next Pages...");
		}

		System.out.println("=================");
		for (Usr user : users) {
			System.out.println(user.getUsername());
		}
		System.out.println("=================");

		model.addAttribute("users", users);
		model.addAttribute("pageinfo", pageinfo);
		String serverip = "";
		String servername = "";
		try {
			InetAddress ia = InetAddress.getLocalHost();
			servername = ia.getHostName();
			serverip = ia.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// model.addAttribute("address", "Test...." + usr.getAddress());
		model.addAttribute("serverip", serverip);
		model.addAttribute("servername", servername);
		return "users";
	}

	@RequestMapping("/getusrsbypage")
	public String getUsersByPageInfo(Model model, @ModelAttribute PageInfo pageinfo) {

		System.out.println("PageInfo before" + pageinfo);
		if (pageinfo == null) {
			pageinfo = new PageInfo();
			pageinfo.setCurrPage(1);
			pageinfo.setFirstPage(1);
			pageinfo.setPageSize(5);

		}
		if (pageinfo.getPageSize() == 0) {
			pageinfo.setCurrPage(1);

			pageinfo.setPageSize(5);
		}
		System.out.println("PageInfo  after: " + pageinfo);
		List<Usr> users = this.usrDAO.listUsrsByPage(pageinfo.getCurrPage(), pageinfo.getPageSize());

		System.out.println("=================");
		for (Usr user : users) {
			System.out.println(user.getUsername());
		}
		System.out.println("=================");

		model.addAttribute("users", users);
		pageinfo.setNextPage(pageinfo.getCurrPage() + 1);
		pageinfo.setPageSize(users.size());
		model.addAttribute("pageinfo", pageinfo);

		// model.addAttribute("address", "Test...." + usr.getAddress());

		return "users";
	}

	@RequestMapping("/todoaddusr")
	public String navToAddusr(Model model) {
		// 视图名，视图为：视图前缀+index+视图后缀，即 /WEB-INF/template/index.html
		model.addAttribute("usr", new Usr());
		return "useradd";
	}

	@RequestMapping("/users")
	public String navToUsrs(Model model) {
		// 视图名，视图为：视图前缀+index+视图后缀，即 /WEB-INF/template/index.html
		model.addAttribute("usr", new Usr());
		return "forward:/get/1/4";
	}

	@RequestMapping("/")
	public String navToHome(Model model) {
		// 视图名，视图为：视图前缀+index+视图后缀，即 /WEB-INF/template/index.html
		// model.addAttribute("usr", new Usr());
		return "forward:/get/1/5";
	}

	@RequestMapping("/addusr")
	public String newone(@ModelAttribute Usr usr) {

		// usr.setBirthday(new Date());

		System.out.println("Sex is : [" + usr.getSex() + "]");
		System.out.println("----New--");
		System.out.println(usr.getAddress());
		System.out.println(usr.getUsername());
		System.out.println(usr.getSex());
		System.out.println(usr.getBirthday());
		System.out.println("------");

		usrDAO.insert(usr);

		// 设置视图名称
		// return "users";
		return "forward:/get/1/5";
	}

	// @RequestMapping("/delusr/{id}")
	// public String deleteUser(@ModelAttribute int id) {

	@RequestMapping("/delusr/{id}")
	public String delUsrs(@PathVariable("id") int id) {
		System.out.println("TODO Delete User ID : " + id);
		usrDAO.deleteUserById(id);
		// 设置视图名称
		// return "users";
		return "forward:/get/1/5";
	}
}
