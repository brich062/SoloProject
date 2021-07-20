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

import com.bronson.sp.models.CartItem;
import com.bronson.sp.models.Product;
import com.bronson.sp.models.User;
import com.bronson.sp.services.CartItemService;
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
	@Autowired
	private CartItemService cServ;
	
	//get mapping for main welcome page
	@GetMapping("/")
	public String welcome(HttpSession session, Model viewModel) {
		Long userId = (Long)session.getAttribute("user_id");
		viewModel.addAttribute("userLoggedIn", userId);
		return "welcome.jsp";
	}
	
	//get mapping for about page
	@GetMapping("/about")
	public String aboutPage(HttpSession session, Model viewModel) {
		Long userId = (Long)session.getAttribute("user_id");
		viewModel.addAttribute("userLoggedIn", userId);
		return "about.jsp";
	}
	
	//get mapping for the products page
	@GetMapping("/products")
	public String products(HttpSession session, Model viewModel) {
		Long userId = (Long)session.getAttribute("user_id");
		viewModel.addAttribute("userLoggedIn", userId);
		return "allProducts.jsp";
	}
	
	//get mapping for individual product
	@GetMapping("/products/{id}")
	public String singleProduct(@PathVariable("id") Long id, Model viewModel, HttpSession session) {
		Long userId = (Long)session.getAttribute("user_id");
		viewModel.addAttribute("userLoggedIn", userId);
		viewModel.addAttribute("product", this.pServ.getById(id));
		System.out.println(this.pServ.getById(id));
		return "prductView.jsp";
	}
	
	
	//get mapping for login/register page
		@GetMapping("/loginregister")
		public String logReg(@ModelAttribute("user") User user) {
			return "loginReg.jsp";
		}
		
		//post mapping for login
		@PostMapping("/login")
		public String login(@RequestParam("email") String email, @RequestParam("password") String password, RedirectAttributes redAttr, HttpSession session) {
			//checking authentication
			if(this.uServ.authenticateUser(email, password)) {
				//login the user
				User user = this.uServ.findByEmail(email);
				session.setAttribute("user_id", user.getId());
				return "redirect:/";
				
			}
			
			//give the custom message if credentials don't match database
			redAttr.addFlashAttribute("errors", "Invalid Credentials");
			return "redirect:/loginregister";
		}
		
		//post mapping for register
		@PostMapping("/register")
		public String registerNew(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
			//validates and gives error message
			validator.validate(user, result);
			//checks for errors and reloads page with message
			if (result.hasErrors()) {
				return "loginReg.jsp";
			}
			//saves the user
			User newUser = this.uServ.registerUser(user); 
			//set the user to the session as if they logged in
			session.setAttribute("user_id", newUser.getId());
			return "redirect:/";
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
		public String likeIdea(@PathVariable("id") Long id, HttpSession session, Product product) {
			Product productLiked = this.pServ.getById(id);
			Long prodId = product.getId();			
			Long userId = (Long)session.getAttribute("user_id");
			User liker = this.uServ.findUserById(userId);
			this.uServ.likeMe(liker, productLiked);
			return "redirect:/products/" + prodId;
		}
		
		//get mapping for unlike
		@GetMapping("/unlike/{id}")
		public String unlikeIdea(@PathVariable("id") Long id, HttpSession session, Product product) {
			Product productLiked = this.pServ.getById(id);
			Long prodId = product.getId();
			Long userId = (Long)session.getAttribute("user_id");
			User liker = this.uServ.findUserById(userId);
			this.uServ.unlikeMe(liker, productLiked);
			return "redirect:/products/" + prodId;
		}
		
		//view your profile
		@GetMapping("/profile/{id}")
		public String viewProfile(@PathVariable("id") Long id, HttpSession session, Model viewModel) {
			Long userId = (Long)session.getAttribute("user_id");
			viewModel.addAttribute("user__id", session.getAttribute("user_id"));
			viewModel.addAttribute("userLoggedIn", userId);
			viewModel.addAttribute("user", this.uServ.findUserById((Long)session.getAttribute("user_id")));
			return "profile.jsp";
		}
		
		//delete your profile
		@GetMapping("/delete/{id}")
		public String delteUser(@PathVariable("id") Long id) {
			this.uServ.deleteUser(id);
			return "redirect:/logout";
		}
		
		//get mapping for edit profile
		@GetMapping("/edit/{id}")
		public String getEditPage(@ModelAttribute("user") User user, @PathVariable("id") Long id, Model viewModel, HttpSession session) {
			Long userId = (Long)session.getAttribute("user_id");
			viewModel.addAttribute("userLoggedIn", userId);
			viewModel.addAttribute("user__id", session.getAttribute("user_id"));
			return "editProfile.jsp";
		}
		
		//post mapping for edit of profile info
		@PostMapping("/edit/{id}")
		public String editEntry(@Valid @ModelAttribute("user") User user, BindingResult result, @PathVariable("id") Long id, Model viewModel, HttpSession session) {
				Long userId = (Long)session.getAttribute("user_id");
			//check for errors and re-render page if there are errors
					if(result.hasErrors()) {
						viewModel.addAttribute("user__id", session.getAttribute("user_id"));
						return "editProfile.jsp";
					}
					//save the new entry
					this.uServ.editUser(user);
					return "redirect:/profile/" + userId ;
		}
		
		
		
		
		
		//post mapping for adding items
		@PostMapping("/additem/{id}")
		public String addItemToCart(@PathVariable("id") Long id, HttpSession session) {
			Product productAdded = this.pServ.getById(id);
			Long userId = (Long)session.getAttribute("user_id");
			User shopper = this.uServ.findUserById(userId);
			this.cServ.addToCart(productAdded, shopper);
			return "redirect:/cart";
		}
		
		//post mapping for deleting an item
		@GetMapping("/cart/delete/{id}")
		public String deleteCartItem(@PathVariable("id") Long id, HttpSession session, CartItem cartItem) {
			/* Long userId = (Long)session.getAttribute("user_id"); */
			Long itemDelete = cartItem.getId();
			this.cServ.deleteFromCart(itemDelete);
			return "redirect:/cart";
		}
		
		
		
		//get mapping for full shopping cart
		@GetMapping("/cart")
		public String fullCart(HttpSession session, Model viewModel) {
			Long userId = (Long)session.getAttribute("user_id");
			viewModel.addAttribute("userLoggedIn", userId);
			User shopper = this.uServ.findUserById(userId);
			viewModel.addAttribute("allItems", this.cServ.getAll(shopper) );
			return "shoppingCart.jsp";
		}
		
}
