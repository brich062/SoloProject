package com.bronson.sp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bronson.sp.models.CartItem;
import com.bronson.sp.models.User;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long> {
	List<CartItem> findAll();
	List<CartItem> findAllByUser(User user);
	
}
