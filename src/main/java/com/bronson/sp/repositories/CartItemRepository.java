package com.bronson.sp.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bronson.sp.models.CartItem;
import com.bronson.sp.models.User;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long> {
	List<CartItem> findAll();
	List<CartItem> findAllByUser(User user);
	List<CartItem> findByUser(User user);
	/*
	 * @Query ("DELETE FROM cartitems c WHERE c.user.id= ?1 AND c.product.id = ?2")
	 * 
	 * @Modifying public void deleteByUserAndProduct(Long userId, Long prodId);
	 */
}
