package com.bronson.sp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bronson.sp.models.CartItem;
import com.bronson.sp.models.Product;
import com.bronson.sp.models.User;
import com.bronson.sp.repositories.CartItemRepository;
import com.bronson.sp.repositories.ProductRepository;
import com.bronson.sp.repositories.UserRepository;

@Service
public class CartItemService {
	@Autowired
	private CartItemRepository cRepo;
	
	@Autowired
	private UserRepository uRepo;
	
	@Autowired
	private ProductRepository pRepo;
	
	//get single cart item
	public CartItem getById(Long id) {
		return this.cRepo.findById(id).orElse(null);
	}
	
	//adding a product to the cart
	public void addToCart(Product product, User user) {
		CartItem cart = new CartItem();
		cart.setProduct(product);
		cart.setUser(user);
		cRepo.save(cart);
	}
	
	//deleting a product from the cart
	public void deleteFromCart(Long id) {
		this.cRepo.deleteById(id);
	}
	
	//get all items in cart
	public List<CartItem> getAll(User user){
		return this.cRepo.findAllByUser(user);
	}
}
