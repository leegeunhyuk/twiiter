package namoo.social.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import namoo.social.entity.User;
import namoo.social.entity.UserToUser;
import namoo.social.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("login.do")	
	public String userLogin(User user, HttpServletRequest req) {
		System.out.println("유저의 아이디는 : " + user.getId());
		if(userService.findUser(user.getId()) == null) {
			System.out.println("유저가 존재하지 않는다. 로그인 실패!");
			return "user/login";
		}
		
		if(userService.findUser(user.getId()).getPassword().equals(user.getPassword())) {
			System.out.println("로그인 성공이다.");
			HttpSession session = req.getSession();
			session.setAttribute("userId", user.getId());
			return "redirect:/message/list.do";
		} else {
			System.out.println("비밀번호를 다시 확인하라. 로그인 실패!");
			return "user/login";
		}
		
	}
	
	@RequestMapping("followingList.do")
	public String showFollowingList(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("userId");
		List<User> followings = userService.findUsersByFromUser(userId);
		
		model.addAttribute("followings", followings);
	
		return "user/followingList";
	}
	
	@RequestMapping("followerList.do")
	public String showFollowerList(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("userId");
		List<User> followers = userService.findUsersByToUser(userId);
		model.addAttribute("followers", followers);
		
		return "user/followerList";
	}
	
	@RequestMapping("unfollow.do")
	public String unfollow(HttpServletRequest req, Model model, String toUserId) {
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userId");
		User fromUser = userService.findUser(userId);
		User toUser = userService.findUser(toUserId);
		UserToUser utu = new UserToUser(fromUser, toUser);
		userService.removeUserToUser(utu);
		return "redirect:/user/followingList.do";
	}
	
	
	@ResponseBody
	@RequestMapping("followCount.do")
	public void followCount(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("userId");
		int followingCount = userService.countOfRelatedUsersByFromUser(userId);
		int followerCount = userService.countOfRelatedUsersByToUser(userId);
		List<Integer> list = new ArrayList<>();
		list.add(followerCount);
		list.add(followingCount);
		try {
			PrintWriter out = resp.getWriter();
			Gson gson = new Gson();
			String json = gson.toJson(list);
			out.print(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
