package com.student.controller;

import com.student.entity.User; // Adjust package if needed
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/") // Ensures correct mapping
public class HomeController {

	  private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	    @GetMapping
	    public String homePage(HttpSession session, Model model) {
	        User user = (User) session.getAttribute("user");
	        if (user == null) {
	            logger.warn("User not found in session!");
	            return "login";
	        }

	        logger.info("User found: {}", user.getUsername());
	        model.addAttribute("userName", user.getUsername());
	        return "index";
	    }
    @GetMapping("/index")
    public String indexPage(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "login";
        }
        return "index";
    }


    @GetMapping("/getUsername")
    @ResponseBody
    public String getUsername(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return (user != null) ? user.getUsername() : "Guest";
    }
}
