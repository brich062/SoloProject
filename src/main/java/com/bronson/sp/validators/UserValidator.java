package com.bronson.sp.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.bronson.sp.models.User;
import com.bronson.sp.repositories.UserRepository;

@Component
public class UserValidator {
	@Autowired
	private UserRepository uRepo;
	
	
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	
	
	
	public void validate(Object target, Errors errors) {
		User user = (User)target;
		
		if (!user.getConfirmPassword().equals(user.getPassword())){
			errors.rejectValue("confirmPassword", "Match", "Passwords do not match");
		}
		
		if (this.uRepo.existsByEmail(user.getEmail())) {
			errors.rejectValue("email", "unique", "Email already exists");
		}
	}
	
}
