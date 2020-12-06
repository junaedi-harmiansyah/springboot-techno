package com.jun.springboot.product.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jun.springboot.product.model.Product;

/**
 * @author ZenBook
 *
 */
public interface ProductDaoImpl extends JpaRepository<Product, Integer>{


	@Query("SELECT p.marketing, COUNT(p.id) FROM Product p GROUP BY  p.marketing")
	public List<String> countMarketplace(); 

}
