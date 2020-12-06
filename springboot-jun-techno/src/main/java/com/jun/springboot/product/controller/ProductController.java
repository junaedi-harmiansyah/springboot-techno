package com.jun.springboot.product.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.jun.springboot.product.model.Product;
import com.jun.springboot.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	/**
	 * Controller product from view
	 */
	@Autowired(required=false)
	private ProductService productService;

	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public ResponseEntity<Collection<Product>> findAll() {
		Collection<Product> listOfProduct = productService.findAll();
		ResponseEntity<Collection<Product>> result = new ResponseEntity<Collection<Product>>(listOfProduct,
				HttpStatus.OK);
		return result;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Integer save(@RequestBody Product product) {
		String username="devloper";
		Integer result = productService.save(product, username);
		return result;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<Product> update(@RequestBody Product product) {
		productService.update(product);
		ResponseEntity<Product> result = new ResponseEntity<Product>(HttpStatus.OK);
		return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> findOne(@PathVariable("id") Integer id) {
		Product product = productService.findOne(id);
		ResponseEntity<Product> result = new ResponseEntity<Product>(product, HttpStatus.OK);
		return result;
	}

	@RequestMapping(value = "/countMarketplace", method = RequestMethod.GET)
	public List<String> countMarketplace() throws JsonProcessingException {
		List<String> listOfProduct = productService.countMarketplace();
		/*
		 * ObjectMapper Obj = new ObjectMapper(); String json =
		 * Obj.writeValueAsString(result);
		 */
		String json = new Gson().toJson(listOfProduct);
		System.out.println(json);
		return listOfProduct;
	}

	@RequestMapping(value = "/saveTerjual", method = RequestMethod.PUT)
	public ResponseEntity<Product> saveTerjual(@RequestBody Product product) {
		productService.saveTerjual(product);
		ResponseEntity<Product> result = new ResponseEntity<Product>(product, HttpStatus.OK);
		return result;

	}
}
