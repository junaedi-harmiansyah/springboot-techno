package com.jun.springboot.security.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jun.springboot.security.entities.Message;
import com.jun.springboot.security.repositories.MessageRepository;

/**
 * @author Junaedi Harmiansyah
 *
 */
@Controller
public class HomeController
{
	@Autowired
	private MessageRepository messageRepository;
	
	@GetMapping("/home")
	public String home(Model model)
	{
		model.addAttribute("msgs", messageRepository.findAll());		
		return "userhome";
	}
	
	@PostMapping("/messages")
	public String saveMessage(Message message)
	{
		messageRepository.save(message);
		return "redirect:/home";
	}
	
	@RequestMapping("/product")
	public String product() {
		return "admin-home/product";
	}

	@RequestMapping("finance")
	public String finance() {
		return "finance";
	}

	@RequestMapping("user")
	public String user() {
		return "user";
	}

	@RequestMapping("market")
	public String market() {
		return "user";
	}
}
