package com.bronson.sp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bronson.sp.models.Product;
import com.bronson.sp.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository pRepo;
	
	
	//find all
	public List<Product> findAll(){
		return this.pRepo.findAll();
	}
	
	//get one product
	public Product getById(Long id) {
		return this.pRepo.findById(id).orElse(null);
	}
}

