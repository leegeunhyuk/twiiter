package namoo.social.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import namoo.social.entity.Message;
import namoo.social.service.MessageService;

@Controller
@RequestMapping("message")
public class MessageController {
	@Autowired
	private MessageService messageService;
	
	@RequestMapping("list.do")
	public String showMessageList(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("userId");
		List<Message> messages = messageService.findMessage(userId);
		model.addAttribute("messages", messages);
		return "message/messageList";
	}
	
	@RequestMapping("regist.do")
	public String registMessage(String contents, Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("userId");
		Message message = new Message();
		message.setContents(contents);
		message.setWriterId(userId);
		
		messageService.registMessage(message);
		
		return "redirect:/message/list.do";
	}
}
