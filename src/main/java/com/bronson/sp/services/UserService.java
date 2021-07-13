package com.bronson.sp.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bronson.sp.models.Product;
import com.bronson.sp.models.User;
import com.bronson.sp.repositories.ProductRepository;
import com.bronson.sp.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository uRepo;
	@Autowired
	private ProductRepository pRepo;
	
	// register user and hash their password
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return uRepo.save(user);
    }
    
    // find user by email
    public User findByEmail(String email) {
    	return this.uRepo.findByEmail(email);
    }    
    // find user by id
    public User findUserById(Long id) {
    	Optional<User> u = uRepo.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = uRepo.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    //edit a user
    public User editUser(User user) {
    	return this.uRepo.save(user);
    }
    
    //delete a user
    public void deleteUser(Long id) {
    	this.uRepo.deleteById(id);
    }
    
    //liking a product
    public void likeMe(User user, Product product) {
    	List<User> allUsers = product.getLikes();
    	allUsers.add(user);
    	this.pRepo.save(product);
    }
    
    //unlike a product
    public void unlikeMe(User user, Product product) {
    	List<User> allUsers = product.getLikes();
    	allUsers.remove(user);
    	this.pRepo.save(product);
    } 
}
