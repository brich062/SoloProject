package com.bronson.sp.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bronson.sp.models.Product;
import com.bronson.sp.models.User;
import com.bronson.sp.services.ProductService;
import com.bronson.sp.services.UserService;
import com.bronson.sp.validators.UserValidator;

@Controller
public class HomeController {
	@Autowired
	private UserService uServ;
	@Autowired
	private UserValidator validator;
	@Autowired
	private ProductService pServ;
	
	//get mapping for login/register page
		@GetMapping("/loginregister")
		public String logReg(@ModelAttribute("user") User user) {
			return "logReg.jsp";
		}
		
		//post mapping for login
		@PostMapping("/login")
		public String login(@RequestParam("email") String email, @RequestParam("password") String password, RedirectAttributes redAttr, HttpSession session) {
			//checking authentication
			if(this.uServ.authenticateUser(email, password)) {
				//login the user
				User user = this.uServ.findByEmail(email);
				session.setAttribute("user_id", user.getId());
				return "redirect:/dashboard";
				
			}
			
			//give the custom message if credentials don't match database
			redAttr.addFlashAttribute("errors", "Invalid Credentials");
			return "redirect:/";
		}
		
		//post mapping for register
		@PostMapping("/register")
		public String registerNew(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
			//validates and gives error message
			validator.validate(user, result);
			//checks for errors and reloads page with message
			if (result.hasErrors()) {
				return "logReg.jsp";
			}
			//saves the user
			User newUser = this.uServ.registerUser(user); 
			//set the user to the session as if they logged in
			session.setAttribute("user_id", newUser.getId());
			return "redirect:/dashboard";
		}
		
		//get mapping for logout
		@GetMapping("/logout")
		public String logout(HttpSession session) {
			//invalidate the users session to log out
			session.invalidate();
			return "redirect:/";
		}
		
		
		
		
		
		
		//get mapping for a like
		@GetMapping("/like/{id}")
		public String likeIdea(@PathVariable("id") Long id, HttpSession session) {
			Product productLiked = this.pServ.getById(id);
			Long userId = (Long)session.getAttribute("user_id");
			User liker = this.uServ.findUserById(userId);
			this.uServ.likeMe(liker, productLiked);
			return "redirect:/dashboard";
		}
		
		//get mapping for unlike
		@GetMapping("/unlike/{id}")
		public String unlikeIdea(@PathVariable("id") Long id, HttpSession session) {
			Product productLiked = this.pServ.getById(id);
			Long userId = (Long)session.getAttribute("user_id");
			User liker = this.uServ.findUserById(userId);
			this.uServ.unlikeMe(liker, productLiked);
			return "redirect:/dashboard";
		}
		
		//delete the entry if you are the user
		@GetMapping("/delete/{id}")
		public String delteUser(@PathVariable("id") Long id) {
			this.uServ.deleteUser(id);
			return "redirect:/dashboard";
		}
		
		//get mapping for edit entry if you are the user
		@GetMapping("/edit/{id}")
		public String getEditPage(@ModelAttribute("user") User user, @PathVariable("id") Long id, Model viewModel, HttpSession session) {
			viewModel.addAttribute("user__id", session.getAttribute("user_id"));
			return "editEntry.jsp";
		}
		
		//post mapping for edit of entry
		@PostMapping("/edit/{id}")
		public String editEntry(@Valid @ModelAttribute("user") User user, BindingResult result, @PathVariable("id") Long id, Model viewModel, HttpSession session) {
			//check for errors and re-render page if there are errors
					if(result.hasErrors()) {
						viewModel.addAttribute("user__id", session.getAttribute("user_id"));
						return "editEntry.jsp";
					}
					//save the new entry
					this.uServ.editUser(user);
					return "redirect:/dashboard";
		}
}
