package com.lord.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lord.model.User;
import com.lord.model.dto.UserDTO;
import com.lord.service.IUserService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class UserAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private IUserService userService;
	private String result;
	private List<UserDTO> userlist;
	
	public UserAction() {
		super();
		System.out.println("Spring 创建实例");
	}

	public String show() {
		System.out.println("Spring 创建实例");
		System.out.println(userService.loadUser(1L).getUserName());
		return SUCCESS;
	}
	
	public String add() {
		User user = new User();
		user.setUserName("洪楚彬");
		user.setPassword("hcb897668");
		userService.save(user);
		return SUCCESS;
	}
	
	public String list() {
		userlist = userService.list();
		try {
			result = JSONUtil.serialize(userlist);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Resource
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<UserDTO> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<UserDTO> userlist) {
		this.userlist = userlist;
	}
	
}
